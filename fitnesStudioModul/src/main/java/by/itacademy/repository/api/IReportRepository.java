package by.itacademy.repository.api;

import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.repository.entity.ReportStatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IReportRepository extends Repository<ReportEntity, UUID> {

    <S extends ReportEntity> S save(S entity);

    Optional<ReportEntity> findById(UUID uuid);

    Page<ReportEntity> findAll(Pageable pageable);

    List<ReportEntity> findFirst10ByStatusIsOrderByDtCreate(
            ReportStatusEntity status);
}
