package by.itacademy.service.util.converters;

import by.itacademy.core.enums.MailStatus;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;

public class UserEntityMailEntityConverter implements Converter<UserEntity, MailEntity> {

    @Override
    public MailEntity convert(UserEntity user) {
        return new MailEntity(user, MailStatus.WAITING);
    }
}