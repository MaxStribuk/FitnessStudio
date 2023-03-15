package by.itacademy.web.util.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.UUID;

public class UuidDeserializer extends JsonDeserializer<UUID> {

    @Override
    public UUID deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        String value = p.getText();
        if (value.length() != 36) {
            return null;
        }
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}