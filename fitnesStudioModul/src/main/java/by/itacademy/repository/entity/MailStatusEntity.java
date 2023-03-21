package by.itacademy.repository.entity;

import by.itacademy.core.enums.MailStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "mail_status", schema = "app")
public class MailStatusEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "mail_status_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "mail_status_seq", sequenceName = "mail_status_id_seq",
            schema = "app", allocationSize = 1)
    private Short id;

    @Enumerated(EnumType.STRING)
    private MailStatus status;

    public MailStatusEntity() {
    }

    public MailStatusEntity(MailStatus status) {
        this.status = status;
        this.id = (short) (status.ordinal() + 1);
    }

    public Short getId() {
        return id;
    }

    public MailStatus getStatus() {
        return status;
    }

    public void setStatus(MailStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailStatusEntity that = (MailStatusEntity) o;
        return Objects.equals(id, that.id)
                && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return "MailStatusEntity{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}