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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "reports", schema = "app")
public class ReportEntity implements Serializable {

    @Id
    @GeneratedValue()
    @Type(type = "pg-uuid")
    @Column(name = "uuid", nullable = false, length = 36)
    private UUID uuid;

    @Column(name = "dt_create", nullable = false)
    @CreationTimestamp
    private LocalDateTime dtCreate;

    @Column(name = "dt_update", nullable = false)
    @Version
    private LocalDateTime dtUpdate;

    @ManyToOne
    @JoinColumn(name = "status")
    private ReportStatusEntity status;

    @ManyToOne
    @JoinColumn(name = "type")
    private ReportTypeEntity type;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "user_id", nullable = false)
    private UUID user;

    @Column(name = "from_", nullable = false)
    private LocalDate from;

    @Column(name = "to_", nullable = false)
    private LocalDate to;

    public void setStatus(ReportStatusEntity status) {
        this.status = status;
    }

    public ReportEntity() {
    }

    public ReportEntity(ReportStatusEntity status, ReportTypeEntity type,
                        String description, UUID user, LocalDate from, LocalDate to) {
        this.status = status;
        this.type = type;
        this.description = description;
        this.user = user;
        this.from = from;
        this.to = to;
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

    public ReportStatusEntity getStatus() {
        return status;
    }

    public ReportTypeEntity getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public UUID getUser() {
        return user;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportEntity that = (ReportEntity) o;
        return Objects.equals(uuid, that.uuid)
                && Objects.equals(dtCreate, that.dtCreate)
                && Objects.equals(dtUpdate, that.dtUpdate)
                && Objects.equals(status, that.status)
                && Objects.equals(type, that.type)
                && Objects.equals(description, that.description)
                && Objects.equals(user, that.user)
                && Objects.equals(from, that.from)
                && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate,
                status, type, description, user, from, to);
    }

    @Override
    public String toString() {
        return "ReportEntity{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", status=" + status +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}