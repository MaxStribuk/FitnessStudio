package by.itacademy.web.util.deserializer;

import by.itacademy.core.enums.EssenceType;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class EssenceTypeDeserializer extends JsonDeserializer<EssenceType> {

    @Override
    public EssenceType deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JacksonException {
        String value = p.getText();
        try {
            return EssenceType.valueOf(value);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
