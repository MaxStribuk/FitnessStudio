package by.itacademy.core.dto.response;

import by.itacademy.core.enums.UserRole;
import by.itacademy.core.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import by.itacademy.web.util.serializers.DateSerializer;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@JsonPropertyOrder({"uuid", "dt_create", "dt_update", "mail", "fio", "role", "status"})
public class PageUserDto implements Serializable {

    private UUID uuid;
    @JsonSerialize(using = DateSerializer.class)
    @JsonProperty("dt_create")
    private LocalDateTime dtCreate;
    @JsonSerialize(using = DateSerializer.class)
    @JsonProperty("dt_update")
    private LocalDateTime dtUpdate;
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;

    public PageUserDto(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                       String mail, String fio, UserRole role, UserStatus status) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageUserDto pageUserDto = (PageUserDto) o;
        return Objects.equals(uuid, pageUserDto.uuid)
                && Objects.equals(dtCreate, pageUserDto.dtCreate)
                && Objects.equals(dtUpdate, pageUserDto.dtUpdate)
                && Objects.equals(mail, pageUserDto.mail)
                && Objects.equals(fio, pageUserDto.fio)
                && role == pageUserDto.role
                && status == pageUserDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate,
                dtUpdate, mail, fio, role, status);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}