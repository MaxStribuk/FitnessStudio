package by.itacademy.repository.entity;

import by.itacademy.core.enums.UserStatus;

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
@Table(name = "user_status", schema = "app")
public class UserStatusEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "user_status_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_status_seq", sequenceName = "user_status_id_seq",
            schema = "app", allocationSize = 1)
    private Short id;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public UserStatusEntity() {
    }

    public UserStatusEntity(UserStatus status) {
        this.status = status;
        this.id = (short) (status.ordinal() + 1);
    }

    public Short getId() {
        return id;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatusEntity that = (UserStatusEntity) o;
        return Objects.equals(id, that.id)
                && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return "UserStatusEntity{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}