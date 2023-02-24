package by.itacademy.repository.api;

import by.itacademy.repository.entity.MailStatus;
import by.itacademy.repository.entity.MailEntity;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

public interface IMailRepository extends Repository<MailEntity, UUID> {

    <S extends MailEntity> S save(S entity);

    List<MailEntity> findFirst10ByStatusIsAndDeparturesAfterOrderByDtCreate(
            MailStatus status, int departures);

    MailEntity findById(UUID uuid);
}