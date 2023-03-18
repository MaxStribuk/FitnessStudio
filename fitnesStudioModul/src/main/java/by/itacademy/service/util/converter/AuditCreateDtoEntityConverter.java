package by.itacademy.service.util.converter;

import by.itacademy.core.dto.request.AuditCreateDto;
import by.itacademy.repository.entity.AuditEntity;
import by.itacademy.repository.entity.EssenceTypeEntity;
import by.itacademy.repository.entity.UserRoleEntity;
import org.springframework.core.convert.converter.Converter;

public class AuditCreateDtoEntityConverter implements Converter<AuditCreateDto, AuditEntity> {

    @Override
    public AuditEntity convert(AuditCreateDto audit) {
        return new AuditEntity(
                audit.getUserUuid(),
                audit.getMail(),
                audit.getFio(),
                new UserRoleEntity(audit.getRole()),
                audit.getText(),
                new EssenceTypeEntity(audit.getType())
        );
    }
}