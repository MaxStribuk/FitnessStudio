package dao.api;

import dao.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAdminDao extends PagingAndSortingRepository<UserEntity, UUID> {

    @Override
    <S extends UserEntity> S save(S entity);

    @Override
    Optional<UserEntity> findById(UUID uuid);

    @Override
    Page<UserEntity> findAll(Pageable pageable);
}