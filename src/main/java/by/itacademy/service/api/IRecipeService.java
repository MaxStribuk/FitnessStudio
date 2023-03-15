package by.itacademy.service.api;

import by.itacademy.core.dto.request.RecipeCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageRecipeDto;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IRecipeService {

    void add(RecipeCreateDto recipe);

    PageDto<PageRecipeDto> getAll(Pageable pageable);

    void update(UUID uuid, LocalDateTime dtUpdate, RecipeCreateDto recipe);
}