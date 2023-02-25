package by.itacademy.service.util.converters;

import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import by.itacademy.service.api.IConverter;

import java.util.stream.Collectors;

public class EntityPageDtoConverter implements IConverter<UserEntity, PageUserDto> {

    @Override
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
