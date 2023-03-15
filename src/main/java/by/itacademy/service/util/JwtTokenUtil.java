package by.itacademy.service.util;

import by.itacademy.service.api.IJwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenUtil implements IJwtTokenUtil {

    private final JwtTokenProperties properties;

    public JwtTokenUtil(JwtTokenProperties properties) {
        this.properties = properties;
    }

    @Override
    public String generateAccessToken(UserDetails user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuer(properties.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()
                        + TimeUnit.DAYS.toMillis(7)))
                .signWith(SignatureAlgorithm.HS512, properties.getSecret())

                .compact();
    }

    @Override
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(properties.getSecret())
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    @Override
    public boolean validate(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(properties.getSecret())
                    .parseClaimsJws(token);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}