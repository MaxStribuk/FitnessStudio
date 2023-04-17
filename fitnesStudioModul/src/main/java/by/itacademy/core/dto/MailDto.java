package by.itacademy.core.dto;

import by.itacademy.repository.entity.MailStatusEntity;
import by.itacademy.repository.entity.UserEntity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class MailDto implements Serializable {

    private final UUID uuid;

    private final UserEntity user;

    private final LocalDateTime dtCreate;

    private final LocalDateTime dtUpdate;

    private String subject;

    private int departures;

    private MailStatusEntity status;

    private UUID verificationCode;

    public MailDto(UUID uuid, UserEntity user, LocalDateTime dtCreate,
                   LocalDateTime dtUpdate, String subject, int departures,
                   MailStatusEntity status, UUID verificationCode) {
        this.uuid = uuid;
        this.user = user;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.subject = subject;
        this.departures = departures;
        this.status = status;
        this.verificationCode = verificationCode;
    }

    public UUID getUuid() {
        return uuid;
    }

    public UserEntity getUser() {
        return user;
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

    public MailStatusEntity getStatus() {
        return status;
    }

    public UUID getVerificationCode() {
        return verificationCode;
    }

    public void setDepartures(int departures) {
        this.departures = departures;
    }

    public void setStatus(MailStatusEntity status) {
        this.status = status;
    }

    public void setVerificationCode(UUID verificationCode) {
        this.verificationCode = verificationCode;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailDto mailDto = (MailDto) o;
        return departures == mailDto.departures
                && Objects.equals(uuid, mailDto.uuid)
                && Objects.equals(user, mailDto.user)
                && Objects.equals(dtCreate, mailDto.dtCreate)
                && Objects.equals(dtUpdate, mailDto.dtUpdate)
                && Objects.equals(subject, mailDto.subject)
                && Objects.equals(status, mailDto.status)
                && Objects.equals(verificationCode, mailDto.verificationCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, user, dtCreate, dtUpdate, subject,
                departures, status, verificationCode);
    }

    @Override
    public String toString() {
        return "MailDto{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", subject='" + subject + '\'' +
                ", departures=" + departures +
                ", status=" + status.getStatus() +
                ", verificationCode=" + verificationCode +
                '}';
    }
}