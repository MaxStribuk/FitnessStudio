package by.itacademy.service.util.converters;

import by.itacademy.core.dto.UserRole;
import by.itacademy.core.dto.UserStatus;
import by.itacademy.core.dto.request.UserRegistrarionDto;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserRegistrarionDtoEntityConverter implements Converter<UserRegistrarionDto, UserEntity> {

    @Override
    public UserEntity convert(@NonNull UserRegistrarionDto user) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return new UserEntity(
                UUID.randomUUID(),
                currentDateTime,
                currentDateTime,
                user.getMail(),
                user.getFio(),
                UserRole.USER,
                UserStatus.WAITING_ACTIVATION,
                user.getPassword()
        );
    }
}