package by.itacademy.service.util.task;

import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.service.api.IReportService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class FileUploader implements Runnable {

    private final ScheduledExecutorService executorService;
    private final IReportService reportService;

    public FileUploader(ScheduledExecutorService executorService,
                        IReportService reportService
    ) {
        this.executorService = executorService;
        this.reportService = reportService;
    }

    @Override
//    @Transactional
    public void run() {
        List<ReportEntity> reports = this.reportService.getUnsent();
        for (ReportEntity report : reports) {
            this.executorService.submit(() ->
                    this.reportService.upload(report));
        }
    }
}