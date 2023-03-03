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
import java.util.Properties;
import java.util.UUID;
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
    private static final String SUBJECT_REGISTRATION = "Registration successfully completed";
    private static final int MAX_SENDS_VERIFICATION_MAIL = 2;
    private static final int MAX_SENDS_REGISTRATION_MAIL = 3;
    private static final long INTERVAL_BETWEEN_SHIPMENTS = 10L;
    private static final String MAIL_TEXT_TYPE = "text/html; charset=utf-8";
    private static final String VERIFICATION_MAIL_TEMPLATE_FILE_NAME = "verification-mail.ftl";
    private static final String REGISTRATION_SUCCESS_MAIL_TEMPLATE_FILE_NAME = "registration-mail.ftl";
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
        UUID uuid = UUID.randomUUID();
        verificationMail.setVerificationCode(uuid);
        verificationMail.setSubject(SUBJECT_VERIFICATION);
        verificationMail.setDepartures(MAX_SENDS_VERIFICATION_MAIL);
        mailRepository.save(verificationMail);
    }

    @Override
    public void addRegistrationCompleteMail(UserEntity user) {
        MailEntity registrationCompleteMail = conversionService.convert(user, MailEntity.class);
        UUID uuid = UUID.randomUUID();
        registrationCompleteMail.setVerificationCode(uuid);
        registrationCompleteMail.setSubject(SUBJECT_REGISTRATION);
        registrationCompleteMail.setDepartures(MAX_SENDS_REGISTRATION_MAIL);
        mailRepository.save(registrationCompleteMail);
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

    @Override
    public void deactivateUser(UserEntity user) {
        user.setStatus(UserStatus.DEACTIVATED);
        adminService.update(
                user.getUuid(),
                user.getDtUpdate(),
                conversionService.convert(user, UserCreateDto.class));
    }

    @Override
    public MailEntity getMail(UserEntity user, UUID verificationCode) {
        return mailRepository.findByUserAndVerificationCode(user, verificationCode);
    }

    private String createTemplateText(MailEntity mail) {
        String subject = mail.getSubject();
        String templateText = null;
        switch (subject) {
            case SUBJECT_VERIFICATION: {
                templateText = createVerificationText(mail);
                break;
            }
            case SUBJECT_REGISTRATION: {
                templateText = createRegistrationText(mail);
                break;
            }
        }
        return templateText;
    }

    private String createVerificationText(MailEntity mail) {
        try {
            Template template = freemarkerConfig.getTemplate(
                    VERIFICATION_MAIL_TEMPLATE_FILE_NAME);
            Properties properties = new Properties();
            properties.put(MAIL_PARAM_USER_NAME, mail.getUser().getFio());
            properties.put(MAIL_PARAM_CODE, mail.getVerificationCode());
            properties.put(MAIL_PARAM_MAIL_ADDRESS, mail.getUser().getMail());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, properties);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException("template with name " +
                    VERIFICATION_MAIL_TEMPLATE_FILE_NAME + " not found");
        }
    }

    private String createRegistrationText(MailEntity mail) {
        try {
            Template template = freemarkerConfig.getTemplate(
                    REGISTRATION_SUCCESS_MAIL_TEMPLATE_FILE_NAME);
            Properties properties = new Properties();
            properties.put(MAIL_PARAM_USER_NAME, mail.getUser().getFio());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, properties);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException("template with name " +
                    REGISTRATION_SUCCESS_MAIL_TEMPLATE_FILE_NAME + " not found");
        }
    }
}