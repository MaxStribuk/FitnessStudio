package by.itacademy.service.api;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJwtTokenUtil {

    String generateAccessToken(UserDetails user);

    String getUsername(String token);

    boolean validate(String token);
}