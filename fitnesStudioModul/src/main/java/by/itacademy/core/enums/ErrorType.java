package by.itacademy.core.enums;

public enum ErrorType {

    ERROR("error"),
    STRUCTURED_ERROR("structured_error");

    private final String description;

    ErrorType(String description) {
        this.description = description;
    }

    public static ErrorType getErrorDescription(String description) {
        for (ErrorType mailStatus : ErrorType.values()) {
            if (mailStatus.description.equals(description)) {
                return mailStatus;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return description;
    }
}