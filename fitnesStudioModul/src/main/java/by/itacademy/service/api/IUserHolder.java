package by.itacademy.service.api;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserHolder {

    UserDetails getCurrentUser();
}