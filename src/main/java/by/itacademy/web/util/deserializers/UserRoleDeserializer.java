package by.itacademy.web.util.deserializers;

import by.itacademy.core.enums.UserRole;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class UserRoleDeserializer extends JsonDeserializer<UserRole> {

    @Override
    public UserRole deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        String value = p.getText();
        try {
            return UserRole.valueOf(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}