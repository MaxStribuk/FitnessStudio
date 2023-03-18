package by.itacademy.core.dto.response;

import by.itacademy.core.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@JsonPropertyOrder({"uuid", "mail", "fio", "role"})
public class UserAuditDto implements Serializable {

    private UUID uuid;

    private String mail;

    private String fio;

    private UserRole role;

    public UserAuditDto(UUID uuid, String mail,
                        String fio, UserRole role) {
        this.uuid = uuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public UUID getUuid() {
        return uuid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuditDto that = (UserAuditDto) o;
        return Objects.equals(uuid, that.uuid)
                && Objects.equals(mail, that.mail)
                && Objects.equals(fio, that.fio)
                && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, mail, fio, role);
    }

    @Override
    public String toString() {
        return "UserAuditDto{" +
                "uuid=" + uuid +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                '}';
    }
}