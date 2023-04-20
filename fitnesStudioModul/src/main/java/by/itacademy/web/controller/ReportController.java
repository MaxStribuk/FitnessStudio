package by.itacademy.web.controller;

import by.itacademy.config.properties.GoogleDriveProperties;
import by.itacademy.core.Constants;
import by.itacademy.core.dto.request.ReportCreateDto;
import by.itacademy.core.dto.response.PageReportDto;
import by.itacademy.core.enums.ReportType;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.service.api.IReportService;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/report")
@Validated
public class ReportController {

    private final IReportService reportService;
    private final GoogleDriveProperties properties;

    public ReportController(IReportService reportService, GoogleDriveProperties properties) {
        this.reportService = reportService;
        this.properties = properties;
    }

    @PostMapping(path = "/{type}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(
            @PathVariable(name = "type") ReportType type,
            @RequestBody @Valid ReportCreateDto report) {
        report.setReportType(type);
        this.reportService.add(report);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDto<PageReportDto>> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @PositiveOrZero(message = "page must be greater than or equal to 0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
            @Positive(message = "size must be greater than 0") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageDto<PageReportDto> pageReport = this.reportService.getAll(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pageReport);
    }

    @GetMapping(path = "/{uuid}/export")
    public ResponseEntity<Resource> get(
            @PathVariable(name = "uuid")
            @Pattern(regexp = Constants.UUID_PATTERN, message = "invalid uuid") String uuid) {
        UUID id = UUID.fromString(uuid);
        boolean isAvailableReport = this.reportService.checkAvailability(id);
        if (isAvailableReport) {
            Resource file = this.reportService.export(id);
            String headerValue = this.properties.getFileAttachment() +
                    uuid + this.properties.getFileExtension();
            return ResponseEntity
                    .ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .contentType(MediaType.parseMediaType(this.properties.getContentType()))
                    .body(file);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.HEAD, path = "/{uuid}/export")
    public ResponseEntity<?> checkAvailability(
            @PathVariable(name = "uuid")
            @Pattern(regexp = Constants.UUID_PATTERN, message = "invalid uuid") String uuid) {
        boolean isAvailableReport = this.reportService.checkAvailability(UUID.fromString(uuid));
        HttpStatus httpStatus = isAvailableReport
                ? HttpStatus.OK
                : HttpStatus.NO_CONTENT;
        return new ResponseEntity<>(httpStatus);
    }
}