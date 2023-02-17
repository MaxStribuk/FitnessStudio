package service.api;

import core.dto.request.UserDto;
import core.dto.response.PageUserDto;
import core.dto.response.PageUsersDto;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IAdminService {

    void add(UserDto user);

    PageUsersDto getAll(int page, int size);

    PageUserDto get(UUID id);

    void update(UUID uuid, LocalDateTime dtUpdate, UserDto user);
}