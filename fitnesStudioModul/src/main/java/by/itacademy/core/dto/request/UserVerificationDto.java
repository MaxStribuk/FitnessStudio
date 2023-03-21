package by.itacademy.core.dto.request;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class UserVerificationDto implements Serializable {

    private UUID uuid;
    private String mail;

    public UUID getUuid() {
        return uuid;
    }

    public String getMail() {
        return mail;
    }

    public UserVerificationDto() {
    }

    public UserVerificationDto(UUID uuid, String mail) {
        this.uuid = uuid;
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVerificationDto that = (UserVerificationDto) o;
        return Objects.equals(uuid, that.uuid)
                && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, mail);
    }

    @Override
    public String toString() {
        return "UserVerificationDto{" +
                "uuid=" + uuid +
                ", mail='" + mail + '\'' +
                '}';
    }
}