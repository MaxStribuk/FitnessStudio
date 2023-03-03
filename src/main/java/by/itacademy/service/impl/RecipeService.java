package by.itacademy.service.impl;

import by.itacademy.core.dto.request.RecipeCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageRecipeDto;
import by.itacademy.service.api.IRecipeService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public class RecipeService implements IRecipeService {

    private final ConversionService conversionService;

    public RecipeService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public void add(RecipeCreateDto recipe) {

    }

    @Override
    public PageDto<PageRecipeDto> getAll(Pageable pageable) {
        return null;
    }

    @Override
    public void update(UUID uuid, LocalDateTime dtUpdate, RecipeCreateDto recipe) {

    }
}