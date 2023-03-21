package by.itacademy.service.util.tasks;

import by.itacademy.core.enums.ReportStatus;
import by.itacademy.repository.entity.AuditEntity;
import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.repository.entity.ReportStatusEntity;
import by.itacademy.service.api.IAuditService;
import by.itacademy.service.api.IExcelFileWriter;
import by.itacademy.service.api.IReportService;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class FtpFileUploader implements Runnable {

    private final ScheduledExecutorService executorService;
    private final IReportService reportService;
    private final IAuditService auditService;
    private final IExcelFileWriter fileWriter;
    private final SessionFactory<FTPFile> sessionFactory;

    public FtpFileUploader(ScheduledExecutorService executorService,
                           IReportService reportService,
                           IAuditService auditService,
                           IExcelFileWriter fileWriter,
                           SessionFactory<FTPFile> sessionFactory) {
        this.executorService = executorService;
        this.reportService = reportService;
        this.auditService = auditService;
        this.fileWriter = fileWriter;
        this.sessionFactory = sessionFactory;
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
                    try (Session<FTPFile> session = sessionFactory.getSession()) {
                        session.write(new ByteArrayInputStream(bytes),
                                report.getUuid().toString() + ".xlsx");
                    }
                    reportEntity.setStatus(new ReportStatusEntity(ReportStatus.DONE));
                } catch (Exception e) {
                    reportEntity.setStatus(new ReportStatusEntity(ReportStatus.ERROR));
                    e.printStackTrace();
                } finally {
                    reportService.add(reportEntity);
                }
            });
        }
    }
}