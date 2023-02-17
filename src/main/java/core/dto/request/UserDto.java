package core.dto.request;

import core.dto.UserRole;
import core.dto.UserStatus;

import java.io.Serializable;
import java.util.Objects;

public class UserDto implements Serializable {

    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private String password;

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

    public UserDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(mail, userDto.mail)
                && Objects.equals(fio, userDto.fio)
                && role == userDto.role
                && status == userDto.status
                && Objects.equals(password, userDto.password);
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