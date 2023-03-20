package by.itacademy.web.controller;

import by.itacademy.core.dto.request.ReportCreateDto;
import by.itacademy.core.dto.response.PageReportDto;
import by.itacademy.core.enums.ReportType;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.service.api.IReportService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/api/v1/report")
@Validated
public class ReportController {

    private final IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping(path = "/{type}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(
            @PathVariable(name = "type") ReportType type,
            @Validated @RequestBody ReportCreateDto report) {
        report.setReportType(type);
        reportService.add(report);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDto<PageReportDto>> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @PositiveOrZero(message = "page must be greater than or equal to 0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
            @Positive(message = "size must be greater than 0") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageDto<PageReportDto> pageReport = reportService.getAll(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pageReport);
    }
}