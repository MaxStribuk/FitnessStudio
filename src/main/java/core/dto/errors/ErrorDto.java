package core.dto.errors;

import java.io.Serializable;

public class ErrorDto implements Serializable {

    private String field;
    private String message;

    public ErrorDto() {
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}