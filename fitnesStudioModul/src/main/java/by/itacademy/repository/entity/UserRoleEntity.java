package by.itacademy.repository.entity;

import by.itacademy.core.enums.UserRole;

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
@Table(name = "user_role", schema = "app")
public class UserRoleEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "role_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_seq", sequenceName = "user_role_id_seq",
            schema = "app", allocationSize = 1)
    private Short id;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserRoleEntity() {
    }

    public UserRoleEntity(UserRole role) {
        this.role = role;
        this.id = (short) (role.ordinal() + 1);
    }

    public Short getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleEntity that = (UserRoleEntity) o;
        return Objects.equals(id, that.id)
                && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "UserRoleEntity{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }
}