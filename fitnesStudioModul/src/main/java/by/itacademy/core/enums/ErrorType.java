package by.itacademy.core.enums;

public enum ErrorType {

    ERROR("error"),
    STRUCTURED_ERROR("structured_error");

    private final String description;

    ErrorType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}