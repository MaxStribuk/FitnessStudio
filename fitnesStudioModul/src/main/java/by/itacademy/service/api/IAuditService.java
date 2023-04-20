package by.itacademy.service.api;

import by.itacademy.core.dto.request.AuditCreateDto;
import by.itacademy.core.dto.response.PageAuditDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.transfer.AuditDto;
import by.itacademy.core.dto.transfer.ReportDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IAuditService {

    void add(AuditCreateDto audit);

    PageDto<PageAuditDto> getAll(Pageable pageable);

    PageAuditDto get(UUID uuid);

    List<AuditDto> getForReport(ReportDto report);
}