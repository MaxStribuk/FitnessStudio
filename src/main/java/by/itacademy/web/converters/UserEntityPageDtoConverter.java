package by.itacademy.web.converters;

import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.dao.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserEntityPageDtoConverter implements Converter<UserEntity, PageUserDto> {

    @Override
    @NonNull
    public PageUserDto convert(UserEntity user) {
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