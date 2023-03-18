package by.itacademy.repository.api;

import by.itacademy.repository.entity.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends Repository<UserEntity, UUID> {

    <S extends UserEntity> S save(S entity);

    Optional<UserEntity> findByMail(String mail);
}