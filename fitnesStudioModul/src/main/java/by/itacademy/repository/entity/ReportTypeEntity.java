package by.itacademy.repository.entity;

import by.itacademy.core.enums.ReportType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "report_type", schema = "app")
public class ReportTypeEntity {

    @Id
    @GeneratedValue(generator = "report_type_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "report_type_seq", sequenceName = "report_type_id_seq",
            schema = "app", allocationSize = 1)
    private Short id;

    @Enumerated(EnumType.STRING)
    private ReportType type;

    public ReportTypeEntity() {
    }

    public ReportTypeEntity(ReportType type) {
        this.type = type;
        this.id = (short) (type.ordinal() + 1);
    }

    public Short getId() {
        return id;
    }

    public ReportType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportTypeEntity that = (ReportTypeEntity) o;
        return Objects.equals(id, that.id)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "ReportTypeEntity{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}