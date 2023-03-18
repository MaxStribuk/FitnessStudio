package by.itacademy.core.dto.request;


import by.itacademy.core.enums.EssenceType;
import by.itacademy.core.enums.UserRole;
import by.itacademy.service.util.validator.Enum;
import by.itacademy.service.util.validator.Uuid;
import by.itacademy.web.util.deserializer.EssenceTypeDeserializer;
import by.itacademy.web.util.deserializer.UserRoleDeserializer;
import by.itacademy.web.util.deserializer.UuidDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class AuditCreateDto implements Serializable {

    @Uuid
    @JsonDeserialize(using = UuidDeserializer.class)
    private UUID userUuid;

    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "invalid email")
    @NotBlank(message = "email cannot be empty")
    private String mail;

    @NotBlank(message = "name cannot be empty")
    private String fio;

    @Enum(clazz = UserRole.class, message = "unknown role")
    @JsonDeserialize(using = UserRoleDeserializer.class)
    private UserRole role;

    @NotBlank(message = "text cannot be empty")
    private String text;

    @Enum(clazz = EssenceType.class, message = "unknown essenceType")
    @JsonDeserialize(using = EssenceTypeDeserializer.class)
    private EssenceType type;

    public AuditCreateDto(UUID userUuid, String mail, String fio,
                          UserRole role, String text, EssenceType type) {
        this.userUuid = userUuid;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.text = text;
        this.type = type;
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
        AuditCreateDto that = (AuditCreateDto) o;
        return Objects.equals(userUuid, that.userUuid)
                && Objects.equals(mail, that.mail)
                && Objects.equals(fio, that.fio)
                && role == that.role
                && Objects.equals(text, that.text)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUuid, mail, fio, role, text, type);
    }

    @Override
    public String toString() {
        return "AuditCreateDto{" +
                "userUuid=" + userUuid +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", text='" + text + '\'' +
                ", type=" + type +
                '}';
    }
}