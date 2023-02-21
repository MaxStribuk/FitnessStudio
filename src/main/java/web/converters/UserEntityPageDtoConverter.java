package web.converters;

import core.dto.response.PageUserDto;
import dao.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

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