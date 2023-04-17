package by.itacademy.service.util.tasks;

import by.itacademy.repository.entity.MailEntity;
import by.itacademy.service.api.ISenderService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@Component
public class EmailSender implements Runnable {

    private final ScheduledExecutorService executorService;
    private final ISenderService senderService;

    public EmailSender(ScheduledExecutorService executorService,
                       ISenderService senderService) {
        this.executorService = executorService;
        this.senderService = senderService;
    }

    @Override
    @Transactional
    public void run() {
        List<MailEntity> emails = this.senderService.getUnsentEmails();
        for (MailEntity email : emails) {
            this.executorService.submit(() ->
                    this.senderService.send(email));
        }
    }
}