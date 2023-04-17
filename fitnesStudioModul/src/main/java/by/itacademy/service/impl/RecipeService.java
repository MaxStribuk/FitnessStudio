package by.itacademy.service.impl;

import by.itacademy.aop.api.Auditable;
import by.itacademy.core.dto.request.IngredientDto;
import by.itacademy.core.dto.request.RecipeCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.core.dto.response.PageRecipeDto;
import by.itacademy.core.enums.EssenceType;
import by.itacademy.core.exception.EntityNotFoundException;
import by.itacademy.core.exception.InvalidVersionException;
import by.itacademy.repository.api.IRecipeRepository;
import by.itacademy.repository.entity.IngredientEntity;
import by.itacademy.repository.entity.ProductEntity;
import by.itacademy.repository.entity.RecipeEntity;
import by.itacademy.service.api.IProductService;
import by.itacademy.service.api.IRecipeService;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecipeService implements IRecipeService {

    private final ConversionService conversionService;
    private final IRecipeRepository recipeRepository;
    private final IProductService productService;
    private final Converter<Page<RecipeEntity>, PageDto<PageRecipeDto>> recipePageDtoConverter;

    public RecipeService(
            ConversionService conversionService,
            IRecipeRepository recipeRepository,
            IProductService productService,
            Converter<Page<RecipeEntity>, PageDto<PageRecipeDto>> recipePageDtoConverter) {
        this.conversionService = conversionService;
        this.recipeRepository = recipeRepository;
        this.productService = productService;
        this.recipePageDtoConverter = recipePageDtoConverter;
    }


    @Override
    @Auditable(value = "added new recipe", type = EssenceType.RECIPE)
    public void add(RecipeCreateDto recipe) {
        RecipeEntity recipeEntity = this.conversionService.convert(recipe, RecipeEntity.class);
        List<IngredientEntity> ingredients = getIngredients(recipe);
        recipeEntity.setProducts(ingredients);
        this.recipeRepository.save(recipeEntity);
    }

    @Override
    public PageDto<PageRecipeDto> getAll(Pageable pageable) {
        Page<RecipeEntity> recipes = this.recipeRepository.findAll(pageable);
        return this.recipePageDtoConverter.convert(recipes);
    }

    @Override
    @Auditable(value = "updated recipe information", type = EssenceType.RECIPE)
    public void update(UUID uuid, LocalDateTime dtUpdate, RecipeCreateDto recipe) {
        Optional<RecipeEntity> recipeEntityOptional = this.recipeRepository.findById(uuid);
        RecipeEntity recipeEntity = recipeEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("recipe not found: " + uuid));
        if (!dtUpdate.isEqual(recipeEntity.getDtUpdate())) {
            throw new InvalidVersionException("invalid dtUpdate");
        }
        update(recipeEntity, recipe);
        this.recipeRepository.save(recipeEntity);
    }

    private void update(RecipeEntity recipeEntity, RecipeCreateDto recipe) {
        recipeEntity.setTitle(recipe.getTitle());
        recipeEntity.setProducts(getIngredients(recipe));
    }

    private List<IngredientEntity> getIngredients(RecipeCreateDto recipe) {
        return recipe.getComposition()
                .stream()
                .map(this::getIngredient)
                .collect(Collectors.toList());
    }

    private IngredientEntity getIngredient(IngredientDto ingredient) {
        UUID uuid = ingredient.getProduct().getUuid();
        PageProductDto pageProductDto = this.productService.get(uuid);
        ProductEntity product = this.conversionService
                .convert(pageProductDto, ProductEntity.class);
        return new IngredientEntity(product, ingredient.getWeight());
    }
}