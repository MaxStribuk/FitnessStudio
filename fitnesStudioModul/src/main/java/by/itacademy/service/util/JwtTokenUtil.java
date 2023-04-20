package by.itacademy.service.util;

import by.itacademy.config.properties.JwtTokenProperties;
import by.itacademy.core.dto.transfer.CurrentUserDto;
import by.itacademy.service.api.IJwtTokenUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenUtil implements IJwtTokenUtil {

    private final JwtTokenProperties properties;

    public JwtTokenUtil(JwtTokenProperties properties) {
        this.properties = properties;
    }

    @Override
    public String generateAccessToken(UserDetails user) {
        CurrentUserDto currentUser = (CurrentUserDto) user;
        Map<String, Object> claims = new HashMap<>();
        claims.put("fio", currentUser.getFio());
        claims.put("role", currentUser.getRole());
        claims.put("status", currentUser.getStatus());
        return Jwts.builder()
                .setSubject(currentUser.getUsername())
                .setIssuer(properties.getIssuer())
                .setId(currentUser.getUuid().toString())
                .addClaims(claims)
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