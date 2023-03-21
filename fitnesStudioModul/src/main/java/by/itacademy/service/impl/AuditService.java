package by.itacademy.service.impl;

import by.itacademy.core.dto.request.AuditCreateDto;
import by.itacademy.core.dto.response.PageAuditDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.exception.DtoNullPointerException;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.repository.api.IAuditRepository;
import by.itacademy.repository.entity.AuditEntity;
import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.service.api.IAuditService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuditService implements IAuditService {

    private final IAuditRepository auditRepository;
    private final ConversionService conversionService;
    private final Converter<Page<AuditEntity>, PageDto<PageAuditDto>> auditPageDtoConverter;

    public AuditService(IAuditRepository auditRepository,
                        @Qualifier("mvcConversionService") ConversionService conversionService,
                        Converter<Page<AuditEntity>, PageDto<PageAuditDto>> auditPageDtoConverter) {
        this.auditRepository = auditRepository;
        this.conversionService = conversionService;
        this.auditPageDtoConverter = auditPageDtoConverter;
    }

    @Override
    public void add(@Validated AuditCreateDto audit) {
        if (audit == null) {
            throw new DtoNullPointerException("userCreateDto must not be null");
        }
        AuditEntity auditEntity = conversionService.convert(audit, AuditEntity.class);
        auditRepository.save(auditEntity);
    }

    @Override
    public PageDto<PageAuditDto> getAll(Pageable pageable) {
        if (pageable == null) {
            throw new NullPointerException("pageable must be not null");
        }
        Page<AuditEntity> audit = auditRepository.findAll(pageable);
        return auditPageDtoConverter.convert(audit);
    }

    @Override
    public PageAuditDto get(UUID uuid) {
        if (uuid == null) {
            throw new EntityNotFoundException("invalid uuid");
        }
        Optional<AuditEntity> auditEntityOptional = auditRepository.findById(uuid);
        AuditEntity auditEntity = auditEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("audit with uuid " + uuid + " not found"));
        return conversionService.convert(auditEntity, PageAuditDto.class);
    }

    @Override
    public List<AuditEntity> getForReport(ReportEntity report) {
        return auditRepository.findByUserUuidEqualsAndDtCreateIsAfterAndDtCreateIsBefore(
                report.getUser(),
                LocalDateTime.of(report.getFrom(), LocalTime.MIN),
                LocalDateTime.of(report.getTo(), LocalTime.MAX)
        );
    }
}