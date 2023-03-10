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
@Table(name = "users", schema = "app")
public class UserEntity implements Serializable {

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

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "fio", nullable = false)
    private String fio;

    @ManyToOne
    @JoinColumn(name = "role")
    private UserRoleEntity role;

    @ManyToOne
    @JoinColumn(name = "status")
    private UserStatusEntity status;

    @Column(name = "password", nullable = false)
    private String password;

    public UserEntity() {
    }

    public UserEntity(String mail, String fio, UserRoleEntity role,
                      UserStatusEntity status, String password) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
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

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public UserRoleEntity getRole() {
        return role;
    }

    public UserStatusEntity getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setRole(UserRoleEntity role) {
        this.role = role;
    }

    public void setStatus(UserStatusEntity status) {
        this.status = status;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(uuid, that.uuid)
                && Objects.equals(dtCreate, that.dtCreate)
                && Objects.equals(dtUpdate, that.dtUpdate)
                && Objects.equals(mail, that.mail)
                && Objects.equals(fio, that.fio)
                && Objects.equals(role, that.role)
                && Objects.equals(status, that.status)
                && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate,
                mail, fio, role, status, password);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}