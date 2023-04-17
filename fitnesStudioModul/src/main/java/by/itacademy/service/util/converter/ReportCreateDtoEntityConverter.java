package by.itacademy.service.util.converter;

import by.itacademy.core.dto.request.ReportCreateDto;
import by.itacademy.core.enums.ReportStatus;
import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.repository.entity.ReportStatusEntity;
import by.itacademy.repository.entity.ReportTypeEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class ReportCreateDtoEntityConverter
        implements Converter<ReportCreateDto, ReportEntity> {

    private static final String DESCRIPTION = "log for ";
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    @Override
    public ReportEntity convert(ReportCreateDto report) {
        return new ReportEntity(
                new ReportStatusEntity(ReportStatus.LOADED),
                new ReportTypeEntity(report.getReportType()),
                createDescription(report),
                report.getUser(),
                report.getFrom(),
                report.getTo()
        );
    }

    private String createDescription(ReportCreateDto report) {
        StringBuilder builder = new StringBuilder(
                report.getReportType()
                .name()
                .toLowerCase()
                .split("_")[1]
        );
        return builder.append("s ")
                .append(DESCRIPTION)
                .append(report.getFrom()
                        .format(DateTimeFormatter.ofPattern(DATE_PATTERN)))
                .append(" - ")
                .append(report.getTo()
                        .format(DateTimeFormatter.ofPattern(DATE_PATTERN)))
                .toString();
    }
}