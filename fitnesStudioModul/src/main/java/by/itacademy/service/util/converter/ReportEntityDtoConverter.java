package by.itacademy.service.util.converter;

import by.itacademy.core.dto.transfer.ReportDto;
import by.itacademy.repository.entity.ReportEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ReportEntityDtoConverter implements Converter<ReportEntity, ReportDto> {

    @Override
    public ReportDto convert(ReportEntity report) {
        return new ReportDto(
                report.getUuid(),
                report.getDtCreate(),
                report.getDtUpdate(),
                report.getStatus().getStatus(),
                report.getType().getType(),
                report.getDescription(),
                report.getUser(),
                report.getFrom(),
                report.getTo()
        );
    }
}