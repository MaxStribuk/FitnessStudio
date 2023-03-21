package by.itacademy.repository.api;

import by.itacademy.repository.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface IProductRepository extends Repository<ProductEntity, UUID> {

    <S extends ProductEntity> S save(S entity);

    Optional<ProductEntity> findById(UUID uuid);

    Page<ProductEntity> findAll(Pageable pageable);
}