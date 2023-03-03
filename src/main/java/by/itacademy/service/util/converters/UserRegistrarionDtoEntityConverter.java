package by.itacademy.service.util.converters;

import by.itacademy.core.enums.UserRole;
import by.itacademy.core.enums.UserStatus;
import by.itacademy.core.dto.request.UserRegistrationDto;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class UserRegistrarionDtoEntityConverter implements Converter<UserRegistrationDto, UserEntity> {

    @Override
    public UserEntity convert(UserRegistrationDto user) {
        return new UserEntity(
                user.getMail(),
                user.getFio(),
                UserRole.USER,
                UserStatus.WAITING_ACTIVATION,
                user.getPassword()
        );
    }
}