package by.itacademy.service.util.converter;

import by.itacademy.core.dto.request.RecipeCreateDto;
import by.itacademy.repository.entity.RecipeEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RecipeCreateDtoEntityConverter
        implements Converter<RecipeCreateDto, RecipeEntity> {

    @Override
    public RecipeEntity convert(RecipeCreateDto recipe) {
        return new RecipeEntity(recipe.getTitle());
    }
}