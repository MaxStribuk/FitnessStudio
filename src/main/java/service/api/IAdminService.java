package service.api;

import core.dto.request.UserCreateDto;
import core.dto.response.PageUserDto;
import core.dto.response.PageUsersDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAdminService {

    void add(UserCreateDto user);

    PageUsersDto getAll(Pageable pageable);

    PageUserDto get(UUID uuid);

    void update(UUID uuid, int dtUpdate, UserCreateDto user);
}