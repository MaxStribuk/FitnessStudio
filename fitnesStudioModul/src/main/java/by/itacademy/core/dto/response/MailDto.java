package by.itacademy.core.dto.response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class MailDto implements Serializable {

    private final UUID uuid;

    private final LocalDateTime dtCreate;

    private final LocalDateTime dtUpdate;

    private final String subject;

    private final int departures;

    private final UUID verificationCode;

    public MailDto(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                   String subject, int departures, UUID verificationCode) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.subject = subject;
        this.departures = departures;
        this.verificationCode = verificationCode;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public String getSubject() {
        return subject;
    }

    public int getDepartures() {
        return departures;
    }

    public UUID getVerificationCode() {
        return verificationCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailDto mailDto = (MailDto) o;
        return departures == mailDto.departures
                && Objects.equals(uuid, mailDto.uuid)
                && Objects.equals(dtCreate, mailDto.dtCreate)
                && Objects.equals(dtUpdate, mailDto.dtUpdate)
                && Objects.equals(subject, mailDto.subject)
                && Objects.equals(verificationCode, mailDto.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, subject, departures, verificationCode);
    }

    @Override
    public String toString() {
        return "MailDto{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", subject='" + subject + '\'' +
                ", departures=" + departures +
                ", verificationCode=" + verificationCode +
                '}';
    }
}
