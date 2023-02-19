package service.impl;

import core.dto.request.UserCreateDto;
import core.dto.response.PageUserDto;
import core.dto.response.PageUsersDto;
import service.api.IAdminService;

import java.util.UUID;

public class AdminService implements IAdminService {

    @Override
    public void add(UserCreateDto user) {

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
    public void update(UUID uuid, int dtUpdate, UserCreateDto user) {

    }
}