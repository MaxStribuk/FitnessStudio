package service.impl;

import core.dto.request.UserDto;
import core.dto.response.PageUserDto;
import core.dto.response.PageUsersDto;
import service.api.IAdminService;

import java.time.LocalDateTime;
import java.util.UUID;

public class AdminService implements IAdminService {

    @Override
    public void add(UserDto user) {

    }

    @Override
    public PageUsersDto getAll(int page, int size) {
        return null;
    }

    @Override
    public PageUserDto get(UUID id) {
        return null;
    }

    @Override
    public void update(UUID uuid, LocalDateTime dtUpdate, UserDto user) {

    }
}