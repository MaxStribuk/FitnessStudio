package by.itacademy.service.api;

import by.itacademy.core.dto.request.ReportCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageReportDto;
import by.itacademy.repository.entity.ReportEntity;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IReportService {

    void add(ReportCreateDto report);

    PageDto<PageReportDto> getAll(Pageable pageable);

    boolean checkAvailability(UUID uuid);

    Resource export(UUID uuid);

    List<ReportEntity> getUnsent();

    void upload(ReportEntity report);
}