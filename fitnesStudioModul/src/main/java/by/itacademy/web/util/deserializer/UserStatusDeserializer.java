package by.itacademy.web.util.deserializer;

import by.itacademy.core.enums.UserStatus;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class UserStatusDeserializer extends JsonDeserializer<UserStatus> {

    @Override
    public UserStatus deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        String value = p.getText();
        try {
            return UserStatus.valueOf(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}