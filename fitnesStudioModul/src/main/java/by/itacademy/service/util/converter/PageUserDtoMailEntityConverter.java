package by.itacademy.service.util.converter;

import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.core.enums.MailStatus;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.MailStatusEntity;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.repository.entity.UserRoleEntity;
import by.itacademy.repository.entity.UserStatusEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PageUserDtoMailEntityConverter implements Converter<PageUserDto, MailEntity> {

    @Override
    public MailEntity convert(PageUserDto user) {
        return new MailEntity(
                new UserEntity(
                        user.getUuid(),
                        user.getDtCreate(),
                        user.getDtUpdate(),
                        user.getMail(),
                        user.getFio(),
                        new UserRoleEntity(user.getRole()),
                        new UserStatusEntity(user.getStatus())
                ),
                new MailStatusEntity(MailStatus.WAITING)
        );
    }
}