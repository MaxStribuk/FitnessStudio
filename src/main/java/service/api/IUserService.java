package service.api;

import core.dto.request.UserLoginDto;
import core.dto.request.UserRegistrarionDto;
import core.dto.response.PageUserDto;

import java.util.UUID;

public interface IUserService {

    void add(UserRegistrarionDto user);

    void verification(UUID verification, String mail);

    void login(UserLoginDto user);

    PageUserDto get();
}