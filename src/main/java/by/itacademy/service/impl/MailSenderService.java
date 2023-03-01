package by.itacademy.service.impl;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.enums.UserStatus;
import by.itacademy.repository.api.IMailRepository;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.api.IAdminService;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.util.EmailSendingThread;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.convert.ConversionService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.time.ZoneOffset;
import java.util.Properties;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MailSenderService implements ISenderService {

    private final IMailRepository mailRepository;
    private final IAdminService adminService;
    private final JavaMailSender mailSender;
    private final Configuration freemarkerConfig;
    private final ScheduledExecutorService executorService;
    private final ConversionService conversionService;
    private static final String SUBJECT_VERIFICATION = "Email address verification";
    private static final int MAX_SENDS_VERIFICATION_MAIL = 2;
    private static final long INTERVAL_BETWEEN_SHIPMENTS = 10L;
    private static final String MAIL_TEXT_TYPE = "text/html; charset=utf-8";
    private static final String MAIL_TEMPLATE_FILE_NAME = "mail-template.ftl";
    private static final String MAIL_PARAM_USER_NAME = "name";
    private static final String MAIL_PARAM_MAIL_ADDRESS = "mail";
    private static final String MAIL_PARAM_CODE = "code";


    public MailSenderService(IMailRepository mailRepository,
                             IAdminService adminService,
                             JavaMailSender mailSender,
                             Configuration freemarkerConfig,
                             ScheduledExecutorService executorService,
                             ConversionService conversionService) {
        this.mailRepository = mailRepository;
        this.adminService = adminService;
        this.mailSender = mailSender;
        this.freemarkerConfig = freemarkerConfig;
        this.executorService = executorService;
        this.conversionService = conversionService;
    }

    @Override
    public void initialize() {
        this.executorService.scheduleWithFixedDelay(
                new EmailSendingThread(executorService, mailRepository, this),
                INTERVAL_BETWEEN_SHIPMENTS,
                INTERVAL_BETWEEN_SHIPMENTS,
                TimeUnit.SECONDS);
    }

    @Override
    public void stop() {
        this.executorService.shutdown();
    }

    @Override
    public void addVerificationMail(UserEntity user) {
        MailEntity verificationMail = conversionService.convert(user, MailEntity.class);
        verificationMail.setSubject(SUBJECT_VERIFICATION);
        verificationMail.setDepartures(MAX_SENDS_VERIFICATION_MAIL);
        mailRepository.save(verificationMail);
    }

    @Override
    public void send(MailEntity mail) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        message.setRecipients(Message.RecipientType.TO,
                mail.getUser().getMail());
        message.setSubject(mail.getSubject());
        String text = createTemplateText(mail);
        message.setContent(text, MAIL_TEXT_TYPE);

        mailSender.send(message);
    }

    public void deactivateUser(UserEntity user){
        user.setStatus(UserStatus.DEACTIVATED);
        adminService.update(
                user.getUuid(),
                user.getDtUpdate().toEpochSecond(ZoneOffset.UTC),
                conversionService.convert(user, UserCreateDto.class));
    }

    private String createTemplateText(MailEntity mail) {
        try {
            Template template = freemarkerConfig.getTemplate(MAIL_TEMPLATE_FILE_NAME);
            Properties properties = new Properties();
            properties.put(MAIL_PARAM_USER_NAME, mail.getUser().getFio());
            properties.put(MAIL_PARAM_CODE, mail.getVerificationCode());
            properties.put(MAIL_PARAM_MAIL_ADDRESS, mail.getUser().getMail());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, properties);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}