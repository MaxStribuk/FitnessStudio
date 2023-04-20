package by.itacademy.service.impl;

import by.itacademy.core.dto.request.AuditCreateDto;
import by.itacademy.core.dto.response.PageAuditDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.transfer.AuditDto;
import by.itacademy.core.dto.transfer.ReportDto;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.repository.api.IAuditRepository;
import by.itacademy.repository.entity.AuditEntity;
import by.itacademy.service.api.IAuditService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Validated
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
    @Transactional
    public void add(AuditCreateDto audit) {
        AuditEntity auditEntity = this.conversionService.convert(audit, AuditEntity.class);
        this.auditRepository.save(auditEntity);
    }

    @Override
    @Transactional
    public PageDto<PageAuditDto> getAll(Pageable pageable) {
        Page<AuditEntity> audit = this.auditRepository.findAll(pageable);
        return this.auditPageDtoConverter.convert(audit);
    }

    @Override
    @Transactional
    public PageAuditDto get(UUID uuid) {
        Optional<AuditEntity> auditEntityOptional = this.auditRepository.findById(uuid);
        AuditEntity auditEntity = auditEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("audit not found " + uuid));
        return this.conversionService.convert(auditEntity, PageAuditDto.class);
    }

    @Override
    @Transactional
    public List<AuditDto> getForReport(ReportDto report) {
        return this.auditRepository
                .findByUserUuidEqualsAndDtCreateIsAfterAndDtCreateIsBefore(
                        report.getUser(),
                        LocalDateTime.of(report.getFrom(), LocalTime.MIN),
                        LocalDateTime.of(report.getTo(), LocalTime.MAX)
                )
                .stream()
                .map(audit -> this.conversionService.convert(audit, AuditDto.class))
                .collect(Collectors.toList());
    }
}