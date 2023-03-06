package by.itacademy.service.util.converters;

import by.itacademy.core.dto.request.RecipeCreateDto;
import by.itacademy.repository.entity.RecipeEntity;
import org.springframework.core.convert.converter.Converter;

public class RecipeCreateDtoEntityConverter
        implements Converter<RecipeCreateDto, RecipeEntity> {

    @Override
    public RecipeEntity convert(RecipeCreateDto recipe) {
        return new RecipeEntity(recipe.getTitle());
    }
}