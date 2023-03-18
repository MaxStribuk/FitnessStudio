package by.itacademy.repository.api;

import by.itacademy.repository.entity.RecipeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Optional;
import java.util.UUID;

public interface IRecipeRepository extends Repository<RecipeEntity, UUID> {

    <S extends RecipeEntity> S save(S entity);

    Optional<RecipeEntity> findById(UUID uuid);

    Page<RecipeEntity> findAll(Pageable pageable);
}