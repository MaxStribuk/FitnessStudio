package by.itacademy.service.util.converters;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class UserCreateDtoEntityConverter implements Converter<UserCreateDto, UserEntity> {

    @Override
    public UserEntity convert(UserCreateDto user) {
        return new UserEntity(
                user.getMail(),
                user.getFio(),
                user.getRole(),
                user.getStatus(),
                user.getPassword()
        );
    }
}