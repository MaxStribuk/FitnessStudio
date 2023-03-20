package by.itacademy.core.dto.request;

import by.itacademy.core.enums.ReportType;
import by.itacademy.service.util.validator.Uuid;
import by.itacademy.web.util.deserializer.LocalDateDeserializer;
import by.itacademy.web.util.deserializer.UuidDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class  ReportCreateDto implements Serializable {

    @Uuid
    @JsonDeserialize(using = UuidDeserializer.class)
    private UUID user;

    @PastOrPresent(message = "the date must not be from the future")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate from;

    @PastOrPresent(message = "the date must not be from the future")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate to;

    private ReportType reportType;

    public ReportCreateDto() {
    }

    public UUID getUser() {
        return user;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportType reportType) {
        this.reportType = reportType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCreateDto that = (ReportCreateDto) o;
        return Objects.equals(user, that.user)
                && Objects.equals(from, that.from)
                && Objects.equals(to, that.to)
                && reportType == that.reportType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, from, to, reportType);
    }

    @Override
    public String toString() {
        return "ReportCreateDto{" +
                "user=" + user +
                ", from=" + from +
                ", to=" + to +
                ", reportType=" + reportType +
                '}';
    }
}