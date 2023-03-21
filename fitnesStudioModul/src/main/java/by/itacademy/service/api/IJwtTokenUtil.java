package by.itacademy.service.api;

import by.itacademy.core.enums.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface IJwtTokenUtil {

    String generateAccessToken(UserDetails user);

    String getUsername(String token);

    String getFio(String token);

    UUID getUuid(String token);

    UserRole getUserRole(String token);

    boolean validate(String token);
}