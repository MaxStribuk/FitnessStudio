package by.itacademy.web.util.deserializer;

import by.itacademy.core.Constants;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

public class UuidDeserializer extends JsonDeserializer<UUID> {

    @Override
    public UUID deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        String value = p.getText();
        try {
            return Pattern.matches(Constants.UUID_PATTERN, value)
                    ? UUID.fromString(value)
                    : null;
        } catch (RuntimeException e) {
            return null;
        }
    }
}