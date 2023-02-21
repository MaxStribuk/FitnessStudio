package web.converters;

import core.dto.response.PageUsersDto;
import dao.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.stream.Collectors;

public class UsersEntityPagesDtoConverter implements Converter<Page<UserEntity>, PageUsersDto> {

    private final UserEntityPageDtoConverter userEntityPageDtoConverter;

    public UsersEntityPagesDtoConverter(UserEntityPageDtoConverter userEntityPageDtoConverter) {
        this.userEntityPageDtoConverter = userEntityPageDtoConverter;
    }

    @Override
    @NonNull
    public PageUsersDto convert(Page<UserEntity> users) {
        return new PageUsersDto(
                users.getNumber(),
                users.getSize(),
                users.getTotalPages(),
                users.getTotalElements(),
                users.isFirst(),
                users.getNumberOfElements(),
                users.isLast(),
                users.stream()
                        .map(userEntityPageDtoConverter::convert)
                        .collect(Collectors.toList()));
    }
}
