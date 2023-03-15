package by.itacademy.repository.api;

import by.itacademy.repository.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface IAdminRepository extends Repository<UserEntity, UUID> {

    <S extends UserEntity> S save(S entity);

    Optional<UserEntity> findById(UUID uuid);

    Page<UserEntity> findAll(Pageable pageable);
}