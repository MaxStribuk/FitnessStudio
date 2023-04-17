package by.itacademy.core.enums;

public enum EmailSubject {

    VERIFICATION("Email address verification"),
    REGISTRATION("Registration successfully completed");

    private final String description;

    EmailSubject(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}