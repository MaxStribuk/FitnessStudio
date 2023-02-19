package service.api;

import core.dto.request.UserCreateDto;
import core.dto.response.PageUserDto;
import core.dto.response.PageUsersDto;

import java.util.UUID;

public interface IAdminService {

    void add(UserCreateDto user);

    PageUsersDto getAll(int page, int size);

    PageUserDto get(UUID id);

    void update(UUID uuid, int dtUpdate, UserCreateDto user);
}