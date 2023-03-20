package by.itacademy.repository.entity;

import by.itacademy.core.enums.ReportStatus;

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
@Table(name = "report_status", schema = "app")
public class ReportStatusEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "report_status_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "report_status_seq", sequenceName = "report_status_id_seq",
            schema = "app", allocationSize = 1)
    private Short id;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    public ReportStatusEntity() {
    }

    public ReportStatusEntity(ReportStatus status) {
        this.status = status;
        this.id = (short) (status.ordinal() + 1);
    }

    public Short getId() {
        return id;
    }

    public ReportStatus getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportStatusEntity that = (ReportStatusEntity) o;
        return Objects.equals(id, that.id)
                && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return "ReportStatusEntity{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}