package by.itacademy.core.dto.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@JsonPropertyOrder({"user", "from", "to"})
public class ReportParamDto implements Serializable {

    private UUID user;

    private LocalDate from;

    private LocalDate to;

    public ReportParamDto(UUID user, LocalDate from, LocalDate to) {
        this.user = user;
        this.from = from;
        this.to = to;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportParamDto that = (ReportParamDto) o;
        return Objects.equals(user, that.user)
                && Objects.equals(from, that.from)
                && Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, from, to);
    }

    @Override
    public String toString() {
        return "ReportParamDto{" +
                "user=" + user +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}