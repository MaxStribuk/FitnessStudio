package by.itacademy.repository.api;

import by.itacademy.repository.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAuditRepository extends Repository<AuditEntity, UUID> {

    <S extends AuditEntity> S save(S entity);

    Optional<AuditEntity> findById(UUID uuid);

    Page<AuditEntity> findAll(Pageable pageable);

        List<AuditEntity> findByUserUuidEqualsAndDtCreateIsAfterAndDtCreateIsBefore(
            UUID userUuid, LocalDateTime dtCreate, LocalDateTime dtCreate2);
}