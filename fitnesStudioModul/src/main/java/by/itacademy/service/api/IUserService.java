package by.itacademy.service.api;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrationDto;
import by.itacademy.core.dto.request.UserVerificationDto;
import by.itacademy.core.dto.response.PageUserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    void add(UserRegistrationDto user);

    void verification(UserVerificationDto user);

    String login(UserLoginDto user);

    PageUserDto get();

    UserDetails loadUserByMail(String mail);
}