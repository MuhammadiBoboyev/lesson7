package uz.pdp.lesson7.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import uz.pdp.lesson7.entity.Role;

import java.util.Date;

@Component
public class JwtProvider {
    private static final long expireDate = 1000 * 60 * 60 * 24;
    private static final String key = "Assalamu1";

    public String generateToken(String username, Role role) {
        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireDate))
                .claim("role", role.getRoleName())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();
    }

    public String getUserName(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception exception) {
            return null;
        }
    }
}
