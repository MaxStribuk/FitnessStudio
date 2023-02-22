package by.itacademy.dao.api;

import by.itacademy.dao.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface IAdminRepository extends Repository<UserEntity, UUID> {

    void save(UserEntity user);

    Optional<UserEntity> findById(UUID uuid);

    Page<UserEntity> findAll(Pageable pageable);
}