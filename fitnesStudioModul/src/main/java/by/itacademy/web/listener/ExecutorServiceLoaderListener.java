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
    private final Runnable fileUploadThread;
    private static final long DELAY_BETWEEN_SHIPMENTS = 10L;
    private static final long INITIAL_DELAY = 10L;

    public ExecutorServiceLoaderListener(
            ScheduledExecutorService executorService,
            @Qualifier("emailSender") Runnable mailSendingThread,
            @Qualifier("fileUploader") Runnable fileUploadThread) {
        this.executorService = executorService;
        this.mailSendingThread = mailSendingThread;
        this.fileUploadThread = fileUploadThread;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextInitialized() {
        executorService.scheduleWithFixedDelay(
                mailSendingThread,
                INITIAL_DELAY,
                DELAY_BETWEEN_SHIPMENTS,
                TimeUnit.SECONDS);
        executorService.scheduleWithFixedDelay(
                fileUploadThread,
                INITIAL_DELAY,
                DELAY_BETWEEN_SHIPMENTS,
                TimeUnit.SECONDS);
    }

    @EventListener(ContextClosedEvent.class)
    public void destroy() {
        executorService.shutdown();
    }
}