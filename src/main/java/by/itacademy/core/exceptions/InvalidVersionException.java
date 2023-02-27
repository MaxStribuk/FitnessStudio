package by.itacademy.core.exceptions;

public class InvalidVersionException extends RuntimeException {

    public InvalidVersionException() {
    }

    public InvalidVersionException(String message) {
        super(message);
    }
}