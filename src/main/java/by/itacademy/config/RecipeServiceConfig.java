package by.itacademy.config;

import by.itacademy.service.api.IRecipeService;
import by.itacademy.service.impl.RecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class RecipeServiceConfig {

    @Bean
    public IRecipeService recipeService(ConversionService conversionService) {
        return new RecipeService(conversionService);
    }
}