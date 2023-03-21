package by.itacademy.service.util.converter;

import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class UserEntityPageDtoConverter implements Converter<UserEntity, PageUserDto> {

    @Override
    public PageUserDto convert(UserEntity user) {
        return new PageUserDto(
                user.getUuid(),
                user.getDtCreate(),
                user.getDtUpdate(),
                user.getMail(),
                user.getFio(),
                user.getRole().getRole(),
                user.getStatus().getStatus()
        );
    }
}