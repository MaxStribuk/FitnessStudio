package by.itacademy.service.impl;

import by.itacademy.core.dto.request.AuditCreateDto;
import by.itacademy.core.dto.response.PageAuditDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.repository.api.IAuditRepository;
import by.itacademy.repository.entity.AuditEntity;
import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.service.api.IAuditService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
@Transactional
public class AuditService implements IAuditService {

    private final IAuditRepository auditRepository;
    private final ConversionService conversionService;
    private final Converter<Page<AuditEntity>, PageDto<PageAuditDto>> auditPageDtoConverter;

    public AuditService(IAuditRepository auditRepository,
                        ConversionService conversionService,
                        Converter<Page<AuditEntity>, PageDto<PageAuditDto>> auditPageDtoConverter) {
        this.auditRepository = auditRepository;
        this.conversionService = conversionService;
        this.auditPageDtoConverter = auditPageDtoConverter;
    }

    @Override
    public void add(@Valid AuditCreateDto audit) {
        AuditEntity auditEntity = this.conversionService.convert(audit, AuditEntity.class);
        this.auditRepository.save(auditEntity);
    }

    @Override
    public PageDto<PageAuditDto> getAll(Pageable pageable) {
        Page<AuditEntity> audit = this.auditRepository.findAll(pageable);
        return this.auditPageDtoConverter.convert(audit);
    }

    @Override
    public PageAuditDto get(UUID uuid) {
        Optional<AuditEntity> auditEntityOptional = this.auditRepository.findById(uuid);
        AuditEntity auditEntity = auditEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("audit not found " + uuid));
        return this.conversionService.convert(auditEntity, PageAuditDto.class);
    }

    @Override
    public List<AuditEntity> getForReport(ReportEntity report) {
        return this.auditRepository
                .findByUserUuidEqualsAndDtCreateIsAfterAndDtCreateIsBefore(
                        report.getUser(),
                        LocalDateTime.of(report.getFrom(), LocalTime.MIN),
                        LocalDateTime.of(report.getTo(), LocalTime.MAX)
                );
    }
}