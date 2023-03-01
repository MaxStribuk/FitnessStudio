package by.itacademy.service.api;

import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.UserEntity;

import javax.mail.MessagingException;

public interface ISenderService {

    void initialize();

    void stop();

    void addVerificationMail(UserEntity user);

    void send(MailEntity mail) throws MessagingException;

    void deactivateUser(UserEntity user);
}