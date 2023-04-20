package by.itacademy.core.dto.transfer;

import by.itacademy.core.enums.EssenceType;
import by.itacademy.core.enums.UserRole;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AuditDto implements Serializable {

    private final UUID uuid;

    private final LocalDateTime dtCreate;

    private final UUID userUuid;

    private final String mail;

    private final String fio;

    private final UserRole role;

    private final String text;

    private final EssenceType type;


    public AuditDto(UUID uuid, LocalDateTime dtCreate, UUID userUuid, String mail,
                    String fio, UserRole role, String text, EssenceType type) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
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

    public UserRole getRole() {
        return role;
    }

    public String getText() {
        return text;
    }

    public EssenceType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditDto auditDto = (AuditDto) o;
        return Objects.equals(uuid, auditDto.uuid)
                && Objects.equals(dtCreate, auditDto.dtCreate)
                && Objects.equals(userUuid, auditDto.userUuid)
                && Objects.equals(mail, auditDto.mail)
                && Objects.equals(fio, auditDto.fio)
                && role == auditDto.role
                && Objects.equals(text, auditDto.text)
                && type == auditDto.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, userUuid, mail, fio, role, text, type);
    }

    @Override
    public String toString() {
        return "AuditDto{" +
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