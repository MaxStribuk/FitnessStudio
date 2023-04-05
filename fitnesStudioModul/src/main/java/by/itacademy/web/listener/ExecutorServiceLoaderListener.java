package by.itacademy.web.listener;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class ExecutorServiceLoaderListener {

    private final ScheduledExecutorService executorService;
    private final Runnable mailSendingThread;
    private final Runnable ftpFileUploader;
    private static final long INTERVAL_BETWEEN_SHIPMENTS = 10L;

    public ExecutorServiceLoaderListener(
            ScheduledExecutorService executorService,
            @Qualifier("emailSender") Runnable mailSendingThread,
            @Qualifier("fileUploader") Runnable ftpFileUploader) {
        this.executorService = executorService;
        this.mailSendingThread = mailSendingThread;
        this.ftpFileUploader = ftpFileUploader;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextInitialized() {
        executorService.scheduleWithFixedDelay(
                mailSendingThread,
                INTERVAL_BETWEEN_SHIPMENTS,
                INTERVAL_BETWEEN_SHIPMENTS,
                TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(
                ftpFileUploader,
                INTERVAL_BETWEEN_SHIPMENTS,
                INTERVAL_BETWEEN_SHIPMENTS,
                TimeUnit.SECONDS);
    }

    @EventListener(ContextClosedEvent.class)
    public void destroy() {
        executorService.shutdown();
    }
}