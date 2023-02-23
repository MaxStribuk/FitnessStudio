package by.itacademy.dao.api;

import by.itacademy.dao.entity.UserEntity;
import org.springframework.data.repository.Repository;

import java.util.UUID;

public interface IUserRepository extends Repository<UserEntity, UUID> {

    void save(UserEntity user);
}