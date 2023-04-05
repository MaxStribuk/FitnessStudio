package by.itacademy.service.util.tasks;

import by.itacademy.core.enums.MailStatus;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.MailStatusEntity;
import by.itacademy.service.api.ISenderService;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class EmailSender implements Runnable {

    private final ScheduledExecutorService executorService;
    private final ISenderService senderService;
    private static final long PAUSE_SENDING_EMAIL = 60L;
    private static final long MILLISECONDS_TO_SEND_EMAIL = 300L;

    public EmailSender(ScheduledExecutorService executorService,
                       ISenderService senderService) {
        this.executorService = executorService;
        this.senderService = senderService;
    }

    @Override
    public void run() {
        List<MailEntity> emails = senderService.getUnsentEmails();
        int countExceptions = 0;
        for (MailEntity email : emails) {
            email.setStatus(new MailStatusEntity(MailStatus.SENT));
            email.setDepartures(email.getDepartures() - 1);
            senderService.add(email);
            final MailEntity mail = senderService.get(email.getUuid());
            try {
                executorService.submit(() -> {
                    try {
                        senderService.send(mail);
                        mail.setStatus(new MailStatusEntity(MailStatus.SUCCESS));
                    } catch (MailSendException e) {
                        Exception[] exceptions = e.getMessageExceptions();
                        for (Exception exception : exceptions) {
                            if (exception instanceof SendFailedException) {
                                mail.setStatus(new MailStatusEntity(MailStatus.ERROR));
                                senderService.deactivateUser(mail.getUser());
                                throw new RuntimeException(e);
                            }
                        }
                        mail.setStatus(new MailStatusEntity(MailStatus.WAITING));
                        throw new RuntimeException(e);
                    } catch (MessagingException e) {
                        mail.setStatus(new MailStatusEntity(MailStatus.WAITING));
                        throw new RuntimeException(e);
                    } finally {
                        senderService.add(mail);
                    }
                }).get(MILLISECONDS_TO_SEND_EMAIL, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                countExceptions++;
            }
        }
        if (countExceptions == emails.size() && emails.size() != 0) {
            try {
                TimeUnit.SECONDS.sleep(PAUSE_SENDING_EMAIL);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}