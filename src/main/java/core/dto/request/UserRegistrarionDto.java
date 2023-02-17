package core.dto.request;

import java.io.Serializable;
import java.util.Objects;

public class UserRegistrarionDto implements Serializable {

    private String mail;
    private String fio;
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