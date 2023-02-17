package core.dto.request;

import java.io.Serializable;
import java.util.Objects;

public class UserLoginDto implements Serializable {

    private String mail;
    private String password;

    public UserLoginDto() {
    }

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