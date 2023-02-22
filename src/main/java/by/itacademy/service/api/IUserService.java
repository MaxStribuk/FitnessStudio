package by.itacademy.service.api;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrarionDto;
import by.itacademy.core.dto.response.PageUserDto;

import java.util.UUID;

public interface IUserService {

    void add(UserRegistrarionDto user);

    void verification(UUID verification, String mail);

    void login(UserLoginDto user);

    PageUserDto get();
}