package by.itacademy.service.util.converters;

import by.itacademy.core.dto.response.PageDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import by.itacademy.service.api.IConverter;

import java.util.stream.Collectors;

public class EntityPageDtoConverter<E, D> implements IConverter<E, D> {

    @Override
    public PageDto<D> convert(Page<E> source, Converter<E, D> converter) {
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
