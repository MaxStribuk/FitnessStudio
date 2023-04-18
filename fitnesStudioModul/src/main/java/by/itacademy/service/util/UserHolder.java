package by.itacademy.service.util;

import by.itacademy.service.api.IUserHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserHolder implements IUserHolder {

    @Override
    public UserDetails getCurrentUser() {
        return (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}