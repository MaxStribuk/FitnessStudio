package by.itacademy.core.dto.error;

import java.io.Serializable;

public class ErrorDto implements Serializable {

    private String field;
    private String message;

    public ErrorDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}