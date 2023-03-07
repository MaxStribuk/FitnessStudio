package by.itacademy.service.util.converters;

import by.itacademy.core.dto.response.IngredientDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.core.dto.response.PageRecipeDto;
import by.itacademy.repository.entity.IngredientEntity;
import by.itacademy.repository.entity.ProductEntity;
import by.itacademy.repository.entity.RecipeEntity;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class RecipeEntityPageDtoConverter
        implements Converter<RecipeEntity, PageRecipeDto> {

    private final Converter<ProductEntity, PageProductDto> converter;

    public RecipeEntityPageDtoConverter(Converter<ProductEntity, PageProductDto> converter) {
        this.converter = converter;
    }

    @Override
    public PageRecipeDto convert(RecipeEntity recipe) {
        List<IngredientEntity> ingredientEntities = recipe.getProducts();
        List<IngredientDto> ingredients = getIngredients(ingredientEntities);
        return createPageRecipe(recipe, ingredients);
    }

    private List<IngredientDto> getIngredients(List<IngredientEntity> ingredientEntities) {
        List<IngredientDto> ingredients = new ArrayList<>();
        for (IngredientEntity ingredient : ingredientEntities) {
            ProductEntity product = ingredient.getProduct();
            int weight = ingredient.getWeight();
            int weightProduct = product.getWeight();
            double coefficient = weight * 1. / weightProduct;
            ingredients.add(createIngredient(product, weight, coefficient));
        }
        return ingredients;
    }

    private IngredientDto createIngredient(ProductEntity product, int weight, double coefficient) {
        return new IngredientDto(
                converter.convert(product),
                weight,
                (int) Math.round(product.getCalories() * coefficient),
                product.getProteins() * coefficient,
                product.getFats() * coefficient,
                product.getCarbohydrates() * coefficient
        );
    }

    private PageRecipeDto createPageRecipe(RecipeEntity recipe, List<IngredientDto> ingredients) {
        return new PageRecipeDto(
                recipe.getUuid(),
                recipe.getDtCreate(),
                recipe.getDtUpdate(),
                recipe.getTitle(),
                ingredients,
                ingredients
                        .stream()
                        .reduce(0,
                                (currentSum, element) -> currentSum + element.getWeight(),
                                Integer::sum),
                ingredients
                        .stream()
                        .reduce(0,
                                (currentSum, element) -> currentSum + element.getCalories(),
                                Integer::sum),
                ingredients
                        .stream()
                        .reduce(0D,
                                (currentSum, element) -> currentSum + element.getProteins(),
                                Double::sum),
                ingredients
                        .stream()
                        .reduce(0D,
                                (currentSum, element) -> currentSum + element.getFats(),
                                Double::sum),
                ingredients
                        .stream()
                        .reduce(0D,
                                (currentSum, element) -> currentSum + element.getCarbohydrates(),
                                Double::sum)
        );
    }
}