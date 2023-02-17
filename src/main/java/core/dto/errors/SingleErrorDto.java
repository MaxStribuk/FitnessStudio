package core.dto.errors;

import java.io.Serializable;

public class SingleErrorDto implements Serializable {

    private String logref;
    private String message;

    public SingleErrorDto() {
    }

    public String getLogref() {
        return logref;
    }

    public String getMessage() {
        return message;
    }
}