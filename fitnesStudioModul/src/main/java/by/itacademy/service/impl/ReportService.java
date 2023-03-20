package by.itacademy.service.impl;

import by.itacademy.aop.api.Auditable;
import by.itacademy.core.dto.request.ReportCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageReportDto;
import by.itacademy.core.enums.EssenceType;
import by.itacademy.core.exception.DtoNullPointerException;
import by.itacademy.repository.api.IReportRepository;
import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.service.api.IAdminService;
import by.itacademy.service.api.IReportService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReportService implements IReportService {

    private final IReportRepository reportRepository;
    private final ConversionService conversionService;
    private final IAdminService adminService;
    private final Converter<Page<ReportEntity>, PageDto<PageReportDto>> reportPageDtoConverter;

    public ReportService(
            IReportRepository reportRepository,
            ConversionService conversionService,
            IAdminService adminService,
            Converter<Page<ReportEntity>, PageDto<PageReportDto>> reportPageDtoConverter) {
        this.reportRepository = reportRepository;
        this.conversionService = conversionService;
        this.adminService = adminService;
        this.reportPageDtoConverter = reportPageDtoConverter;
    }

    @Override
    @Auditable(value = "added new report", type = EssenceType.REPORT)
    public void add(ReportCreateDto report) {
        if (report == null) {
            throw new DtoNullPointerException("reportParamDto must not be null");
        }
        adminService.get(report.getUser());
        ReportEntity reportEntity = conversionService.convert(report, ReportEntity.class);
        reportRepository.save(reportEntity);
    }

    @Override
    public PageDto<PageReportDto> getAll(Pageable pageable) {
        if (pageable == null) {
            throw new NullPointerException("pageable must be not null");
        }
        Page<ReportEntity> reports = reportRepository.findAll(pageable);
        return reportPageDtoConverter.convert(reports);
    }
}