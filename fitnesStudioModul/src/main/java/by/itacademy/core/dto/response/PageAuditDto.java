package by.itacademy.core.dto.response;

import by.itacademy.core.enums.EssenceType;
import by.itacademy.web.util.serializer.DateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@JsonPropertyOrder({"uuid", "dt_create", "user", "text", "type", "id"})
public class PageAuditDto implements Serializable {

    private UUID uuid;

    @JsonSerialize(using = DateSerializer.class)
    @JsonProperty("dt_create")
    private LocalDateTime dtCreate;

    private UserAuditDto user;

    private String text;

    private EssenceType type;

    private String id;

    public PageAuditDto(UUID uuid, LocalDateTime dtCreate, UserAuditDto user,
                        String text, EssenceType type, String id) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.user = user;
        this.text = text;
        this.type = type;
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public UserAuditDto getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public EssenceType getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageAuditDto that = (PageAuditDto) o;
        return Objects.equals(uuid, that.uuid)
                && Objects.equals(dtCreate, that.dtCreate)
                && Objects.equals(user, that.user)
                && Objects.equals(text, that.text)
                && type == that.type
                && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, user, text, type, id);
    }

    @Override
    public String toString() {
        return "PageAuditDto{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}