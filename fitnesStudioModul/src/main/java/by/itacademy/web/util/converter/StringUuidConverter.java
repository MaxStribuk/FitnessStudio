package by.itacademy.web.util.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.UUID;

public class StringUuidConverter implements Converter<String, UUID> {

    public static final String UUID_REGEX =
            "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-4[a-fA-F0-9]{3}-[89abAB][a-fA-F0-9]{3}-[a-fA-F0-9]{12}";

    @Override
    public UUID convert(String source) {
        try {
            return source.matches(UUID_REGEX)
                    ? UUID.fromString(source)
                    : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}