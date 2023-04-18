package by.itacademy.service.util.task;

import by.itacademy.core.enums.ReportStatus;
import by.itacademy.repository.entity.AuditEntity;
import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.repository.entity.ReportStatusEntity;
import by.itacademy.service.api.IAuditService;
import by.itacademy.service.api.IExcelFileWriter;
import by.itacademy.service.api.IFileHandlingService;
import by.itacademy.service.api.IReportService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class FileUploader implements Runnable {

    private final ScheduledExecutorService executorService;
    private final IReportService reportService;
    private final IAuditService auditService;
    private final IExcelFileWriter fileWriter;
    private final IFileHandlingService fileHandlingService;

    public FileUploader(ScheduledExecutorService executorService,
                        IReportService reportService,
                        IAuditService auditService,
                        IExcelFileWriter fileWriter,
                        IFileHandlingService fileHandlingService) {
        this.executorService = executorService;
        this.reportService = reportService;
        this.auditService = auditService;
        this.fileWriter = fileWriter;
        this.fileHandlingService = fileHandlingService;
    }

    @Override
    public void run() {
        List<ReportEntity> reports = reportService.getUnsent();
        for (ReportEntity report : reports) {
            report.setStatus(new ReportStatusEntity(ReportStatus.PROGRESS));
            reportService.add(report);
            final ReportEntity reportEntity = reportService.get(report.getUuid());
            executorService.submit(() -> {
                List<AuditEntity> audits = auditService.getForReport(report);
                try {
                    byte[] bytes = fileWriter.write(audits);
                    fileHandlingService.upload(report.getUuid().toString(), bytes);
                    reportEntity.setStatus(new ReportStatusEntity(ReportStatus.DONE));
                } catch (Exception e) {
                    reportEntity.setStatus(new ReportStatusEntity(ReportStatus.ERROR));
                } finally {
                    reportService.add(reportEntity);
                }
            });
        }
    }
}