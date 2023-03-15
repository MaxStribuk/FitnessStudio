package by.itacademy.core.enums;

public enum MailStatus {

    WAITING("email waiting to be sent"),
    SENT("email is sent"),
    ERROR("email sending error"),
    SUCCESS("email sent successfully");

    private final String description;

    MailStatus(String description) {
        this.description = description;
    }

    public static MailStatus getEmailStatus(String description) {
        for (MailStatus mailStatus : MailStatus.values()){
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