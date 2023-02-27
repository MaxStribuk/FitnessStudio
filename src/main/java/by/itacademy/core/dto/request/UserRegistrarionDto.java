package by.itacademy.core.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class UserRegistrarionDto implements Serializable {

    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
            message = "invalid email")
    @NotBlank(message = "email cannot be empty")
    private String mail;

    @NotBlank(message = "name cannot be empty")
    private String fio;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 5, message = "password must contain at least 5 characters")
    private String password;

    public UserRegistrarionDto() {
    }

    public String getMail() {
        return mail;
    }

    public String getFio() {
        return fio;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRegistrarionDto that = (UserRegistrarionDto) o;
        return Objects.equals(mail, that.mail)
                && Objects.equals(fio, that.fio)
                && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, fio, password);
    }

    @Override
    public String toString() {
        return "UserRegistrarionDto{" +
                "mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }
}