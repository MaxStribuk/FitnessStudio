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
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "audit", schema = "app")
public class AuditEntity implements Serializable {

    @Id
    @GeneratedValue()
    @Type(type = "pg-uuid")
    @Column(name = "uuid", nullable = false, length = 36)
    private UUID uuid;

    @Column(name = "dt_create", nullable = false)
    @CreationTimestamp
    private LocalDateTime dtCreate;

    @Column(name = "user_uuid", nullable = false)
    private UUID userUuid;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "fio", nullable = false)
    private String fio;

    @ManyToOne
    @JoinColumn(name = "role")
    private UserRoleEntity role;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "essence_type")
    private EssenceTypeEntity type;

    public AuditEntity() {
    }

    public AuditEntity(UUID userUuid, String mail, String fio,
                       UserRoleEntity role, String text, EssenceTypeEntity type) {
        this.userUuid = userUuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.text = text;
        this.type = type;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public UUID getUserUuid() {
        return userUuid;
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

    public String getText() {
        return text;
    }

    public EssenceTypeEntity getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditEntity that = (AuditEntity) o;
        return Objects.equals(uuid, that.uuid)
                && Objects.equals(dtCreate, that.dtCreate)
                && Objects.equals(userUuid, that.userUuid)
                && Objects.equals(mail, that.mail)
                && Objects.equals(fio, that.fio)
                && Objects.equals(role, that.role)
                && Objects.equals(text, that.text)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, userUuid, mail, fio, role, text, type);
    }

    @Override
    public String toString() {
        return "AuditEntity{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", userUuid=" + userUuid +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}