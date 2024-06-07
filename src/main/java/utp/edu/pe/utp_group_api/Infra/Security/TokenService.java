package utp.edu.pe.utp_group_api.Infra.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class TokenService {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    private static final SecretKey SIGNING_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    public String generarToken(Authentication authentication) {
        String username = authentication.getName();
        Date timeNow = new Date();
        Date expirationTime = new Date(timeNow.getTime() + EXPIRATION_TIME);
        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(timeNow)
                .setExpiration(expirationTime)
                .signWith(SIGNING_KEY, SignatureAlgorithm.HS512)
                .compact();
        return token;
    }

    public String obtenerUsernameDeJWT(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(SIGNING_KEY).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("El Token ha expirado o fue alterado");
        }
    }
}

