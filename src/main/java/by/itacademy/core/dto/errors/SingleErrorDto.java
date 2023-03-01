package by.itacademy.core.dto.errors;

import java.io.Serializable;

public class SingleErrorDto implements Serializable {

    private String logref;
    private String message;

    public SingleErrorDto(String logref, String message) {
        this.logref = logref;
        this.message = message;
    }

    public String getLogref() {
        return logref;
    }

    public String getMessage() {
        return message;
    }
}