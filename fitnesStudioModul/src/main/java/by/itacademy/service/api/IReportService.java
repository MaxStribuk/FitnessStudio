package by.itacademy.service.api;

import by.itacademy.core.dto.request.ReportCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageReportDto;
import org.springframework.data.domain.Pageable;

public interface IReportService {

    void add(ReportCreateDto report);

    PageDto<PageReportDto> getAll(Pageable pageable);
}