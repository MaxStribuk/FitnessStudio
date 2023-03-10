package by.itacademy.service.util.converters;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class UserEntityCreationDtoConverter implements Converter<UserEntity, UserCreateDto> {

    @Override
    public UserCreateDto convert(UserEntity user) {
        return new UserCreateDto(
                user.getMail(),
                user.getFio(),
                user.getRole().getRole(),
                user.getStatus().getStatus(),
                user.getPassword()
        );
    }
}