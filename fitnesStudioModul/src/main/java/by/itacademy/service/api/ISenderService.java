package by.itacademy.service.api;

import by.itacademy.core.dto.response.MailDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface ISenderService {

    void create(PageUserDto user, String subject);

    List<MailEntity> getUnsentEmails();

    MailDto getMail(UserEntity user, UUID verificationCode);

    void send(MailEntity mail);
}