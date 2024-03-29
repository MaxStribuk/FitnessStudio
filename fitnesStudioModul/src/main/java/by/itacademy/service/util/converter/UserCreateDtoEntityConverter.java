package by.itacademy.service.util.converter;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.repository.entity.UserRoleEntity;
import by.itacademy.repository.entity.UserStatusEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserCreateDtoEntityConverter implements Converter<UserCreateDto, UserEntity> {

    @Override
    public UserEntity convert(UserCreateDto user) {
        return new UserEntity(
                user.getMail(),
                user.getFio(),
                new UserRoleEntity(user.getRole()),
                new UserStatusEntity(user.getStatus()),
                user.getPassword()
        );
    }
}