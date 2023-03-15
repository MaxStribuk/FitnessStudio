package by.itacademy.repository.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "mails", schema = "app")
public class MailEntity implements Serializable {

    @Id
    @GeneratedValue()
    @Type(type = "pg-uuid")
    @Column(name = "uuid", nullable = false, length = 36)
    private UUID uuid;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "dt_create", nullable = false)
    @CreationTimestamp
    private LocalDateTime dtCreate;

    @Column(name = "dt_update", nullable = false)
    @Version
    private LocalDateTime dtUpdate;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "departures", nullable = false)
    private int departures;

    @ManyToOne
    @JoinColumn(name = "status")
    private MailStatusEntity status;

    @Type(type = "pg-uuid")
    @Column(name = "code", length = 36)
    private UUID verificationCode;

    public MailEntity() {
    }

    public MailEntity(UserEntity user, MailStatusEntity status) {
        this.user = user;
        this.status = status;
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

    public UUID getVerificationCode() {
        return verificationCode;
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

    public void setSubject(String subject) {
        this.subject = subject;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailEntity that = (MailEntity) o;
        return departures == that.departures
                && Objects.equals(uuid, that.uuid)
                && Objects.equals(user, that.user)
                && Objects.equals(dtCreate, that.dtCreate)
                && Objects.equals(dtUpdate, that.dtUpdate)
                && Objects.equals(verificationCode, that.verificationCode)
                && Objects.equals(subject, that.subject)
                && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, user, dtCreate, dtUpdate,
                verificationCode, subject, departures, status);
    }

    @Override
    public String toString() {
        return "MailEntity{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", verificationCode='" + verificationCode + '\'' +
                ", subject='" + subject + '\'' +
                ", departures=" + departures +
                ", status=" + status +
                '}';
    }
}