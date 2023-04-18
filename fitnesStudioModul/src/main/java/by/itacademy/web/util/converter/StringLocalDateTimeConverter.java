package by.itacademy.web.util.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class StringLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String dtUpdate) {
        try {
            long longDtUpdate = Long.parseLong(dtUpdate);
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(longDtUpdate), ZoneOffset.UTC);
        } catch (RuntimeException e) {
            return null;
        }
    }
}