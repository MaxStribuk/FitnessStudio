package by.itacademy.service.api;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrarionDto;
import by.itacademy.core.dto.request.UserVerificationDto;
import by.itacademy.core.dto.response.PageUserDto;

public interface IUserService {

    void add(UserRegistrarionDto user);

    void verification(UserVerificationDto user);

    void login(UserLoginDto user);

    PageUserDto get();
}