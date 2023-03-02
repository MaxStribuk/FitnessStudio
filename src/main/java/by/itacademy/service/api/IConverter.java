package by.itacademy.service.api;

import by.itacademy.core.dto.response.PageDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

public interface IConverter<E, D> {

    PageDto<D> convert(Page<E> source, Converter<E, D> converter);
}