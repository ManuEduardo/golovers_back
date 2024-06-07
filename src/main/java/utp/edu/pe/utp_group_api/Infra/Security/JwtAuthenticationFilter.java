package utp.edu.pe.utp_group_api.Infra.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final CustomUsersDetailsService customUsersDetailsService;
    private final TokenService jwtGenerator;

    @Autowired
    public JwtAuthenticationFilter(CustomUsersDetailsService customUsersDetailsService, TokenService jwtGenerator) {
        this.customUsersDetailsService = customUsersDetailsService;
        this.jwtGenerator = jwtGenerator;
    }

    private String obtenerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = obtenerToken(request);
        if (StringUtils.hasText(token)) {
            try {
                if (jwtGenerator.validarToken(token)) {
                    var username = jwtGenerator.obtenerUsernameDeJWT(token);
                    var userDetails = customUsersDetailsService.loadUserByUsername(username);
                    List<String> userRoles = userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority).toList();
                    if (userRoles.contains("ROLE_STUDENT") || userRoles.contains("ROLE_ADMIN")) {
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No tienes permisos para acceder a este recurso");
                        return;
                    }
                }
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido: " + e.getMessage());
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
