package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserLoginDto;
import by.itacademy.core.dto.request.UserRegistrarionDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {

    @Override
    public void add(UserRegistrarionDto user) {

    }

    @Override
    public void verification(UUID verification, String mail) {

    }

    @Override
    public void login(UserLoginDto user) {

    }

    @Override
    public PageUserDto get() {
        return null;
    }
}