package utp.edu.pe.utp_group_api.Infra.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
/**
 * JwtAuthenticationEntryPoint se encarga de manejar las excepciones de autenticación
 * enviando un error HTTP 401 (Unauthorized) cuando el usuario no está autenticado.
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * Este método se llama cuando se produce una excepción de autenticación.
     * Envía un error HTTP 401 (Unauthorized) en la respuesta.
     *
     * @param request   el HttpServletRequest
     * @param response  el HttpServletResponse
     * @param authException la excepción de autenticación que se ha producido
     * @throws IOException si ocurre un error de entrada/salida
     * @throws ServletException si ocurre un error en el servlet
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
