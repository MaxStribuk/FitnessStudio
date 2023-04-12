package by.itacademy.core.dto.request;

import by.itacademy.core.Constants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class UserRegistrationDto implements Serializable {

    @Email(regexp = Constants.EMAIL_PATTERN, message = "invalid email")
    private String mail;

    @NotBlank(message = "name cannot be empty")
    private String fio;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 5, message = "password must contain at least 5 characters")
    private String password;

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
        UserRegistrationDto that = (UserRegistrationDto) o;
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
        return "UserRegistrationDto{" +
                "mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }
}