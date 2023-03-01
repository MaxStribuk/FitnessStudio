package by.itacademy.repository.api;

import by.itacademy.core.enums.MailStatus;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface IMailRepository extends Repository<MailEntity, UUID> {

    <S extends MailEntity> S save(S entity);

    List<MailEntity> findFirst10ByStatusIsAndDeparturesAfterOrderByDtCreate(
            MailStatus status, int departures);

    MailEntity findByUserAndVerificationCode(UserEntity user, UUID verificationCode);

    MailEntity findById(UUID uuid);
}