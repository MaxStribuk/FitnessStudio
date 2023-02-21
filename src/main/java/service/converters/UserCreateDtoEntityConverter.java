package service.converters;

import core.dto.request.UserCreateDto;
import dao.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserCreateDtoEntityConverter implements Converter<UserCreateDto, UserEntity> {

    @Override
    @NonNull
    public UserEntity convert(@NonNull UserCreateDto user) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return new UserEntity(
                UUID.randomUUID(),
                currentDateTime,
                currentDateTime,
                user.getMail(),
                user.getFio(),
                user.getRole(),
                user.getStatus(),
                user.getPassword()
        );
    }
}