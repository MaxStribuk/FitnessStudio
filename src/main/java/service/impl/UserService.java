package service.impl;

import core.dto.request.UserLoginDto;
import core.dto.request.UserRegistrarionDto;
import core.dto.response.PageUserDto;
import service.api.IUserService;

import java.util.UUID;

public class UserService implements IUserService {

    @Override
    public void add(UserRegistrarionDto user) {

    }

    @Override
    public void verification(UUID verification) {

    }

    @Override
    public void login(UserLoginDto user) {

    }

    @Override
    public PageUserDto get() {
        return null;
    }
}