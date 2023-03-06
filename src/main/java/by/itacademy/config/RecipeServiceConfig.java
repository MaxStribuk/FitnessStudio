package by.itacademy.config;

import by.itacademy.repository.api.IRecipeRepository;
import by.itacademy.service.api.IProductService;
import by.itacademy.service.api.IRecipeService;
import by.itacademy.service.impl.RecipeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class RecipeServiceConfig {

    @Bean
    public IRecipeService recipeService(ConversionService conversionService,
                                        IRecipeRepository recipeRepository,
                                        IProductService productService) {
        return new RecipeService(conversionService, recipeRepository, productService);
    }
}