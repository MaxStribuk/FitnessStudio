package by.itacademy.core.dto.response;

import by.itacademy.core.enums.ReportStatus;
import by.itacademy.core.enums.ReportType;
import by.itacademy.web.util.serializer.DateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@JsonPropertyOrder({"uuid", "dt_create", "dt_update", "status", "type", "description", "status"})
public class PageReportDto implements Serializable {

    private UUID uuid;

    @JsonSerialize(using = DateSerializer.class)
    @JsonProperty("dt_create")
    private LocalDateTime dtCreate;

    @JsonSerialize(using = DateSerializer.class)
    @JsonProperty("dt_update")
    private LocalDateTime dtUpdate;

    private ReportStatus status;

    private ReportType type;

    private String description;

    private ReportParamDto params;

    public PageReportDto(UUID uuid, LocalDateTime dtCreate,
                         LocalDateTime dtUpdate, ReportStatus status,
                         ReportType type, String description, ReportParamDto params) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.status = status;
        this.type = type;
        this.description = description;
        this.params = params;
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

    public ReportStatus getStatus() {
        return status;
    }

    public ReportType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public ReportParamDto getParams() {
        return params;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PageReportDto that = (PageReportDto) o;
        return Objects.equals(uuid, that.uuid)
                && Objects.equals(dtCreate, that.dtCreate)
                && Objects.equals(dtUpdate, that.dtUpdate)
                && status == that.status
                && type == that.type
                && Objects.equals(description, that.description)
                && Objects.equals(params, that.params);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, status, type, description, params);
    }

    @Override
    public String toString() {
        return "PageReportDto{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", status=" + status +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", params=" + params +
                '}';
    }
}