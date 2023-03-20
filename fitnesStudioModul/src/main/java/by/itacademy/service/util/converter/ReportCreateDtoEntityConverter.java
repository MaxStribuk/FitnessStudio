package by.itacademy.service.util.converter;

import by.itacademy.core.dto.request.ReportCreateDto;
import by.itacademy.core.enums.ReportStatus;
import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.repository.entity.ReportStatusEntity;
import by.itacademy.repository.entity.ReportTypeEntity;
import org.springframework.core.convert.converter.Converter;

import java.time.format.DateTimeFormatter;

public class ReportCreateDtoEntityConverter implements Converter<ReportCreateDto, ReportEntity> {

    private static final String DESCRIPTION = "log for ";
    private static final String DATE_PATTERN = "dd.MM.yyyy";

    @Override
    public ReportEntity convert(ReportCreateDto report) {
        return new ReportEntity(
                new ReportStatusEntity(ReportStatus.PROGRESS),
                new ReportTypeEntity(report.getReportType()),
                createDescription(report),
                report.getUser(),
                report.getFrom(),
                report.getTo()
        );
    }

    private String createDescription(ReportCreateDto report) {
        String reportType = report.getReportType()
                .name()
                .toLowerCase()
                .split("_")[1];
        return reportType
                + "s "
                + DESCRIPTION
                + report.getFrom().format(DateTimeFormatter.ofPattern(DATE_PATTERN))
                + " - "
                + report.getTo().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}