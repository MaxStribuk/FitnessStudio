package by.itacademy.service.util.converters;

import by.itacademy.core.dto.response.PageDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.util.stream.Collectors;

public class EntityPageDtoConverter<E, D> implements Converter<Page<E>, PageDto<D>> {

    private final Converter<E, D> converter;

    public EntityPageDtoConverter(Converter<E, D> converter) {
        this.converter = converter;
    }

    @Override
    public PageDto<D> convert(Page<E> source) {
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