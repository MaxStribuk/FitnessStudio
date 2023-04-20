package by.itacademy.core.dto.transfer;

import by.itacademy.core.enums.ReportStatus;
import by.itacademy.core.enums.ReportType;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class ReportDto implements Serializable {

    private UUID uuid;

    private LocalDateTime dtCreate;

    private LocalDateTime dtUpdate;

    private ReportStatus status;

    private ReportType type;

    private String description;

    private UUID user;

    private LocalDate from;

    private LocalDate to;

    public ReportDto(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                     ReportStatus status, ReportType type, String description,
                     UUID user, LocalDate from, LocalDate to) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.status = status;
        this.type = type;
        this.description = description;
        this.user = user;
        this.from = from;
        this.to = to;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getUser() {
        return user;
    }

    public void setUser(UUID user) {
        this.user = user;
    }

    public LocalDate getFrom() {
        return from;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDto reportDto = (ReportDto) o;
        return Objects.equals(uuid, reportDto.uuid)
                && Objects.equals(dtCreate, reportDto.dtCreate)
                && Objects.equals(dtUpdate, reportDto.dtUpdate)
                && status == reportDto.status
                && type == reportDto.type
                && Objects.equals(description, reportDto.description)
                && Objects.equals(user, reportDto.user)
                && Objects.equals(from, reportDto.from)
                && Objects.equals(to, reportDto.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, status,
                type, description, user, from, to);
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", status=" + status +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}