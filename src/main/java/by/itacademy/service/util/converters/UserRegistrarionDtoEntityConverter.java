package by.itacademy.service.util.converters;

import by.itacademy.core.enums.UserRole;
import by.itacademy.core.enums.UserStatus;
import by.itacademy.core.dto.request.UserRegistrationDto;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserRegistrarionDtoEntityConverter implements Converter<UserRegistrationDto, UserEntity> {

    @Override
    public UserEntity convert(@NonNull UserRegistrationDto user) {
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