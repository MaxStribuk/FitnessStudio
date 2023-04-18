package by.itacademy.service.api;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrationDto;
import by.itacademy.core.dto.request.UserVerificationDto;
import by.itacademy.core.dto.response.PageUserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    void create(UserRegistrationDto user);

    void verify(UserVerificationDto user);

    String logIn(UserLoginDto user);

    PageUserDto get();

    UserDetails loadUserByMail(String mail);
}