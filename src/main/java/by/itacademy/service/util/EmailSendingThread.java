package by.itacademy.service.util;

import by.itacademy.repository.api.IMailRepository;
import by.itacademy.core.enums.MailStatus;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.service.api.ISenderService;
import org.springframework.mail.MailSendException;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EmailSendingThread implements Runnable {

    private final ScheduledExecutorService executorService;
    private final IMailRepository mailRepository;
    private final ISenderService senderService;
    private static final long PAUSE_SENDING_EMAIL = 60L;
    private static final long MILLISECONDS_TO_SEND_EMAIL = 300L;

    public EmailSendingThread(ScheduledExecutorService executorService,
                              IMailRepository mailRepository,
                              ISenderService senderService) {
        this.executorService = executorService;
        this.mailRepository = mailRepository;
        this.senderService = senderService;
    }

    @Override
    public void run() {
        List<MailEntity> emails =
                mailRepository.findFirst10ByStatusIsAndDeparturesAfterOrderByDtCreate(
                        MailStatus.WAITING, 0);
        int countExceptions = 0;
        for (MailEntity email : emails) {
            email.setStatus(MailStatus.SENT);
            email.setDepartures(email.getDepartures() - 1);
            mailRepository.save(email);
            final MailEntity mail = mailRepository.findById(email.getUuid());
            try {
                executorService.submit(() -> {
                    try {
                        senderService.send(mail);
                        mail.setStatus(MailStatus.SUCCESS);
                    } catch (MailSendException e) {
                        Exception[] exceptions = e.getMessageExceptions();
                        for (Exception exception : exceptions) {
                            if (exception instanceof SendFailedException) {
                                mail.setStatus(MailStatus.ERROR);
                                senderService.deactivateUser(mail.getUser());
                                throw new RuntimeException(e);
                            }
                        }
                        mail.setStatus(MailStatus.WAITING);
                        throw new RuntimeException(e);
                    } catch (MessagingException e) {
                        mail.setStatus(MailStatus.WAITING);
                        throw new RuntimeException(e);
                    } finally {
                        mailRepository.save(mail);
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