package by.itacademy.core.dto.errors;

import java.io.Serializable;
import java.util.List;

public class MultipleErrorDto implements Serializable {

    private String logref;
    private List<ErrorDto> errors;

    public MultipleErrorDto() {
    }

    public String getLogref() {
        return logref;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }
}