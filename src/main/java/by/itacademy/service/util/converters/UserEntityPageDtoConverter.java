package by.itacademy.service.util.converters;

import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

public class UserEntityPageDtoConverter implements Converter<UserEntity, PageUserDto> {

    @Override
    public PageUserDto convert(@NonNull UserEntity user) {
        return new PageUserDto(
                user.getUuid(),
                user.getDtCreate(),
                user.getDtUpdate(),
                user.getMail(),
                user.getFio(),
                user.getRole(),
                user.getStatus()
        );
    }
}