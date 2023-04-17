package by.itacademy.service.util.converter;

import by.itacademy.core.dto.response.MailDto;
import by.itacademy.repository.entity.MailEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MailEntityDtoConverter implements Converter<MailEntity, MailDto> {

    @Override
    public MailDto convert(MailEntity mail) {
        return new MailDto(
                mail.getUuid(),
                mail.getUser(),
                mail.getDtCreate(),
                mail.getDtUpdate(),
                mail.getSubject(),
                mail.getDepartures(),
                mail.getStatus(),
                mail.getVerificationCode()
        );
    }
}