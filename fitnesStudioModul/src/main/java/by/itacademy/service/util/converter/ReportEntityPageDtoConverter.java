package by.itacademy.service.util.converter;

import by.itacademy.core.dto.response.PageReportDto;
import by.itacademy.core.dto.response.ReportParamDto;
import by.itacademy.repository.entity.ReportEntity;
import org.springframework.core.convert.converter.Converter;

public class ReportEntityPageDtoConverter implements Converter<ReportEntity, PageReportDto> {

    @Override
    public PageReportDto convert(ReportEntity report) {
        return new PageReportDto(
                report.getUuid(),
                report.getDtCreate(),
                report.getDtUpdate(),
                report.getStatus().getStatus(),
                report.getType().getType(),
                report.getDescription(),
                new ReportParamDto(
                        report.getUser(),
                        report.getFrom(),
                        report.getTo()
                )
        );
    }
}