package by.itacademy.core.dto.request;

import by.itacademy.core.enums.UserRole;
import by.itacademy.core.enums.UserStatus;
import by.itacademy.service.util.validator.Enum;
import by.itacademy.web.util.deserializer.UserRoleDeserializer;
import by.itacademy.web.util.deserializer.UserStatusDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class UserCreateDto implements Serializable {

    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "invalid email")
    @NotBlank(message = "email cannot be empty")
    private String mail;

    @NotBlank(message = "name cannot be empty")
    private String fio;

    @Enum(clazz = UserRole.class, message = "unknown role")
    @JsonDeserialize(using = UserRoleDeserializer.class)
    private UserRole role;

    @Enum(clazz = UserStatus.class, message = "unknown status")
    @JsonDeserialize(using = UserStatusDeserializer.class)
    private UserStatus status;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 5, message = "password must contain at least 5 characters")
    private String password;

    public UserCreateDto() {
    }

    public UserCreateDto(String mail, String fio,
                         UserRole role, UserStatus status, String password) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
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

    public UserStatus getStatus() {
        return status;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateDto userCreateDto = (UserCreateDto) o;
        return Objects.equals(mail, userCreateDto.mail)
                && Objects.equals(fio, userCreateDto.fio)
                && role == userCreateDto.role
                && status == userCreateDto.status
                && Objects.equals(password, userCreateDto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, fio, role, status, password);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}