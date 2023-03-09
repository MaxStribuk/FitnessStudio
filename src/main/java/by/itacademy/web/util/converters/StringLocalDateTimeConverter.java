package by.itacademy.web.util.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class StringLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String dtUpdate) {
        try {
            long longDtUpdate = Long.parseLong(dtUpdate);
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(longDtUpdate), ZoneOffset.UTC);
        } catch (NumberFormatException | DateTimeException e) {
            return null;
        }
    }
}