package web.converters;

import core.dto.response.PageDto;
import core.dto.response.PageUserDto;
import dao.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;
import service.api.IConverter;

import java.util.stream.Collectors;

public class EntityPageDtoConverter implements IConverter<UserEntity, PageUserDto> {

    @Override
    @NonNull
    public PageDto<PageUserDto> convert(Page<UserEntity> source,
                                        Converter<UserEntity, PageUserDto> converter) {
        return new PageDto<>(
                source.getNumber(),
                source.getSize(),
                source.getTotalPages(),
                source.getTotalElements(),
                source.isFirst(),
                source.getNumberOfElements(),
                source.isLast(),
                source.stream()
                        .map(converter::convert)
                        .collect(Collectors.toList()));
    }
}
