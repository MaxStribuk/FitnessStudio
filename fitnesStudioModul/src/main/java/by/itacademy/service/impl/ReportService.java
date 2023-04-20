package by.itacademy.service.impl;

import by.itacademy.aop.api.Auditable;
import by.itacademy.core.dto.request.ReportCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageReportDto;
import by.itacademy.core.dto.transfer.AuditDto;
import by.itacademy.core.dto.transfer.ReportDto;
import by.itacademy.core.enums.EssenceType;
import by.itacademy.core.enums.ReportStatus;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.core.exception.FileDownloadException;
import by.itacademy.repository.api.IReportRepository;
import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.repository.entity.ReportStatusEntity;
import by.itacademy.service.api.IAuditService;
import by.itacademy.service.api.IExcelFileWriter;
import by.itacademy.service.api.IFileHandlingService;
import by.itacademy.service.api.IReportService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReportService implements IReportService {

    private final IReportRepository reportRepository;
    private final ConversionService conversionService;
    private final IAuditService auditService;
    private final Converter<Page<ReportEntity>, PageDto<PageReportDto>> reportPageDtoConverter;
    private final IFileHandlingService fileHandlingService;
    private final IExcelFileWriter fileWriter;

    public ReportService(
            IReportRepository reportRepository,
            ConversionService conversionService,
            IAuditService auditService,
            Converter<Page<ReportEntity>, PageDto<PageReportDto>> reportPageDtoConverter,
            IFileHandlingService fileHandlingService,
            IExcelFileWriter fileWriter) {
        this.reportRepository = reportRepository;
        this.conversionService = conversionService;
        this.auditService = auditService;
        this.reportPageDtoConverter = reportPageDtoConverter;
        this.fileHandlingService = fileHandlingService;
        this.fileWriter = fileWriter;
    }

    @Override
    @Transactional
    @Auditable(value = "added new report", type = EssenceType.REPORT)
    public void add(ReportCreateDto report) {
        ReportEntity reportEntity = this.conversionService.convert(report, ReportEntity.class);
        this.reportRepository.save(reportEntity);
    }

    @Override
    @Transactional
    public PageDto<PageReportDto> getAll(Pageable pageable) {
        Page<ReportEntity> reports = this.reportRepository.findAll(pageable);
        return this.reportPageDtoConverter.convert(reports);
    }

    @Override
    @Transactional
    public List<ReportEntity> getUnsent() {
        return this.reportRepository.findFirst10ByStatusIsOrderByDtCreate(
                new ReportStatusEntity(ReportStatus.LOADED)
        );
    }

    @Override
    public void upload(ReportEntity report) {
        report.setStatus(new ReportStatusEntity(ReportStatus.PROGRESS));
        this.reportRepository.save(report);
        try {
            report = this.reportRepository
                    .findById(report.getUuid())
                    .get();
            List<AuditDto> audits = this.auditService.getForReport(
                    this.conversionService.convert(report, ReportDto.class));
            byte[] bytes = this.fileWriter.write(audits);
            this.fileHandlingService.upload(report.getUuid().toString(), bytes);
            report.setStatus(new ReportStatusEntity(ReportStatus.DONE));
        } catch (Exception e) {
            report.setStatus(new ReportStatusEntity(ReportStatus.ERROR));
        } finally {
            this.reportRepository.save(report);
        }
    }

    @Override
    @Transactional
    public boolean checkAvailability(UUID uuid) {
        Optional<ReportEntity> reportEntityOptional = this.reportRepository.findById(uuid);
        ReportEntity reportEntity = reportEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("report not found: " + uuid));
        return reportEntity.getStatus().getStatus().equals(ReportStatus.DONE);
    }

    @Override
    @Transactional
    public Resource export(UUID uuid) {
        boolean isAvailableReport = checkAvailability(uuid);
        if (isAvailableReport) {
            try {
                return this.fileHandlingService.download(uuid.toString());
            } catch (IOException e) {
                throw new FileDownloadException("File download failed", e);
            }
        } else {
            throw new FileDownloadException("File not found: " + uuid);
        }
    }
}