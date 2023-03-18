package by.itacademy.service.util.converter;

import by.itacademy.core.dto.response.PageAuditDto;
import by.itacademy.core.dto.response.UserAuditDto;
import by.itacademy.repository.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;

public class AuditEntityPageDtoConverter implements Converter<AuditEntity, PageAuditDto> {

    @Override
    public PageAuditDto convert(AuditEntity audit) {
        return new PageAuditDto(
                audit.getUuid(),
                audit.getDtCreate(),
                new UserAuditDto(
                        audit.getUserUuid(),
                        audit.getMail(),
                        audit.getFio(),
                        audit.getRole().getRole()
                ),
                audit.getText(),
                audit.getType().getType(),
                audit.getType().getId().toString()
        );
    }
}