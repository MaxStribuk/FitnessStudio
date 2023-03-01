package by.itacademy.service.util.converters;

import by.itacademy.core.enums.MailStatus;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserEntityMailEntityConverter implements Converter<UserEntity, MailEntity> {

    @Override
    public MailEntity convert(@NonNull UserEntity user) {
        LocalDateTime currentTime = LocalDateTime.now();
        return new MailEntity(
                UUID.randomUUID(),
                user,
                currentTime,
                currentTime,
                UUID.randomUUID(),
                MailStatus.WAITING);
    }
}