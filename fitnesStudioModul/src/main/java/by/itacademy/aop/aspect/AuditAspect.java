package by.itacademy.aop.aspect;

import by.itacademy.core.dto.request.AuditCreateDto;
import by.itacademy.core.dto.transfer.CurrentUserDto;
import by.itacademy.service.api.IUserHolder;
import by.itacademy.service.impl.AuditService;
import by.itacademy.aop.api.Auditable;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class AuditAspect {

    private final IUserHolder holder;
    private final AuditService auditService;

    public AuditAspect(IUserHolder holder, AuditService auditService) {
        this.holder = holder;
        this.auditService = auditService;
    }

    @AfterReturning("@annotation(auditable)")
    @Transactional
    public void afterReturningAddOrUpdateEssenceAdvice(Auditable auditable) {
        CurrentUserDto user = (CurrentUserDto) this.holder.getCurrentUser();
        AuditCreateDto auditCreateDto = new AuditCreateDto(
                user.getUuid(),
                user.getUsername(),
                user.getFio(),
                user.getRole(),
                auditable.value(),
                auditable.type()
        );
        this.auditService.add(auditCreateDto);
    }
}