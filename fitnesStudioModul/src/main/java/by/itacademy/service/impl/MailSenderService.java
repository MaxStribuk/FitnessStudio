package by.itacademy.service.impl;

import by.itacademy.config.properties.EmailProperties;
import by.itacademy.core.dto.MailDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.core.enums.EmailSubject;
import by.itacademy.core.enums.MailStatus;
import by.itacademy.repository.api.IMailRepository;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.MailStatusEntity;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.api.ISenderService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.core.convert.ConversionService;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

@Service
public class MailSenderService implements ISenderService {

    private final IMailRepository mailRepository;
    private final JavaMailSender mailSender;
    private final Configuration freemarkerConfig;
    private final ConversionService conversionService;
    private final EmailProperties properties;

    public MailSenderService(IMailRepository mailRepository,
                             JavaMailSender mailSender,
                             Configuration freemarkerConfig,
                             ConversionService conversionService,
                             EmailProperties properties) {
        this.mailRepository = mailRepository;
        this.mailSender = mailSender;
        this.freemarkerConfig = freemarkerConfig;
        this.conversionService = conversionService;
        this.properties = properties;
    }

    @Override
    @Transactional
    public void create(PageUserDto user, String subject) {
        MailEntity mail = this.conversionService.convert(user, MailEntity.class);
        UUID uuid = UUID.randomUUID();
        mail.setVerificationCode(uuid);
        mail.setSubject(subject);
        mail.setDepartures(properties.getMaxSend());
        this.mailRepository.save(mail);
    }

    @Override
    @Transactional
    public List<MailEntity> getUnsentEmails() {
        return this.mailRepository
                .findFirst10ByStatusIsAndDeparturesAfterOrderByDtCreate(
                        new MailStatusEntity(MailStatus.WAITING), 0);
    }

    @Override
    public void send(MailEntity mail) {
        MimeMessage message = this.mailSender.createMimeMessage();
        prepareMessage(mail, message);
        try {
            mail.setStatus(new MailStatusEntity(MailStatus.SENT));
            mail.setDepartures(mail.getDepartures() - 1);
            this.mailRepository.save(mail);
            mail = this.mailRepository.findById(mail.getUuid());
            this.mailSender.send(message);
            mail.setStatus(new MailStatusEntity(MailStatus.SUCCESS));
        } catch (MailSendException e) {
            boolean isValidMail = checkValidMail(e);
            mail.setStatus(
                    isValidMail
                    ? new MailStatusEntity(MailStatus.WAITING)
                    : new MailStatusEntity(MailStatus.ERROR)
            );
        } finally {
            this.mailRepository.save(mail);
        }
    }

    @Override
    @Transactional
    public MailDto getMail(UserEntity user, UUID verificationCode) {
        MailEntity mail = this.mailRepository
                .findByUserAndVerificationCode(user, verificationCode)
                .orElse(null);
        if (mail == null) {
            return null;
        }
        return this.conversionService.convert(mail, MailDto.class);
    }

    private boolean checkValidMail(MailSendException e) {
        Exception[] exceptions = e.getMessageExceptions();
        for (Exception exception : exceptions) {
            if (exception instanceof SendFailedException) {
                return false;
            }
        }
        return true;
    }

    private void prepareMessage(MailEntity mail, MimeMessage message) {
        try {
            message.setRecipients(Message.RecipientType.TO,
                    mail.getUser().getMail());
            message.setSubject(mail.getSubject());
            String text = createTemplateText(mail);
            message.setContent(text, this.properties.getTextType());
        } catch (MessagingException e) {
            throw new RuntimeException("MimeMessage preparation failed", e);
        }
    }

    private String createTemplateText(MailEntity mail) {
        boolean isVerificationMail = mail
                .getSubject()
                .equals(EmailSubject.VERIFICATION.toString());
        return isVerificationMail
                ? createMailText(mail, this.properties.getVerificationFileName())
                : createMailText(mail, this.properties.getRegistrationFileName());
    }

    private String createMailText(MailEntity mail, String templateFileName) {
        try {
            Template template = this.freemarkerConfig.getTemplate(templateFileName);
            Properties properties = new Properties();
            properties.put(this.properties.getUserNameParamName(), mail.getUser().getFio());
            properties.put(this.properties.getMailAdressParamName(), mail.getUser().getMail());
            properties.put(this.properties.getVerificationCodeParamName(), mail.getVerificationCode());
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, properties);
        } catch (IOException | TemplateException e) {
            throw new MailPreparationException("template not found: " + templateFileName);
        }
    }
}