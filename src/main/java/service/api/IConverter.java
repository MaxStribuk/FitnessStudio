package service.api;

import core.dto.response.PageDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

public interface IConverter<E, D> {

    @NonNull
    PageDto<D> convert(Page<E> source, Converter<E, D> converter);
}