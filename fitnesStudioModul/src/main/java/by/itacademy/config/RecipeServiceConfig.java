package by.itacademy.config;

import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageRecipeDto;
import by.itacademy.repository.api.IRecipeRepository;
import by.itacademy.repository.entity.RecipeEntity;
import by.itacademy.service.api.IProductService;
import by.itacademy.service.api.IRecipeService;
import by.itacademy.service.impl.RecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

@Configuration
public class RecipeServiceConfig {

    @Bean
    public IRecipeService recipeService(
            ConversionService conversionService,
            IRecipeRepository recipeRepository,
            IProductService productService,
            Converter<Page<RecipeEntity>, PageDto<PageRecipeDto>> recipePageDtoConverter) {
        return new RecipeService(conversionService,
                recipeRepository,
                productService,
                recipePageDtoConverter);
    }
}