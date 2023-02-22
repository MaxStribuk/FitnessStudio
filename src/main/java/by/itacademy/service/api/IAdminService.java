package by.itacademy.service.api;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.core.dto.response.PageDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface IAdminService {

    void add(UserCreateDto user);

    PageDto<PageUserDto> getAll(Pageable pageable);

    PageUserDto get(UUID uuid);

    void update(UUID uuid, int dtUpdate, UserCreateDto user);
}