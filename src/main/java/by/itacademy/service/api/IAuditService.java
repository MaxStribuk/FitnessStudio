package by.itacademy.service.api;

import by.itacademy.core.dto.request.AuditCreateDto;
import by.itacademy.core.dto.response.PageAuditDto;
import by.itacademy.core.dto.response.PageDto;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

public interface IAuditService {

    void add(@Validated AuditCreateDto audit);

    PageDto<PageAuditDto> getAll(Pageable pageable);

    PageAuditDto get(UUID uuid);
}