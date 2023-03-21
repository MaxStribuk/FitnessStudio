package by.itacademy.repository.entity;

import by.itacademy.core.enums.EssenceType;

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
@Table(name = "essence_type", schema = "app")
public class EssenceTypeEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "essence_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "essence_seq", sequenceName = "essence_type_id_seq",
            schema = "app", allocationSize = 1)
    private Short id;

    @Enumerated(EnumType.STRING)
    private EssenceType type;

    public EssenceTypeEntity() {
    }

    public EssenceTypeEntity(EssenceType type) {
        this.type = type;
        this.id = (short) (type.ordinal() + 1);

    }

    public Short getId() {
        return id;
    }

    public EssenceType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EssenceTypeEntity that = (EssenceTypeEntity) o;
        return Objects.equals(id, that.id)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "EssenceTypeEntity{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
}