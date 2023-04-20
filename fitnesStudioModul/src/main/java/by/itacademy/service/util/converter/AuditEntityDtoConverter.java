package by.itacademy.service.util.converter;

import by.itacademy.core.dto.transfer.AuditDto;
import by.itacademy.repository.entity.AuditEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuditEntityDtoConverter implements Converter<AuditEntity, AuditDto> {

    @Override
    public AuditDto convert(AuditEntity audit) {
        return new AuditDto(
                audit.getUuid(),
                audit.getDtCreate(),
                audit.getUserUuid(),
                audit.getMail(),
                audit.getFio(),
                audit.getRole().getRole(),
                audit.getText(),
                audit.getType().getType()
        );
    }
}