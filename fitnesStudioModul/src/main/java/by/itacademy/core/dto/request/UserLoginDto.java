package by.itacademy.core.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

public class UserLoginDto implements Serializable {

    @Email(regexp = UserRegistrationDto.EMAIL_PATTERN, message = "invalid email")
    private String mail;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 5, message = "password must contain at least 5 characters")
    private String password;

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginDto that = (UserLoginDto) o;
        return Objects.equals(mail, that.mail)
                && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, password);
    }

    @Override
    public String toString() {
        return "UserLoginDto{" +
                "mail='" + mail + '\'' +
                '}';
    }
}