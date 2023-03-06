package by.itacademy.service.impl;

import by.itacademy.core.dto.request.IngredientDto;
import by.itacademy.core.dto.request.RecipeCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.core.dto.response.PageRecipeDto;
import by.itacademy.core.exceptions.DtoNullPointerException;
import by.itacademy.core.exceptions.EntityNotFoundException;
import by.itacademy.core.exceptions.InvalidVersionException;
import by.itacademy.repository.api.IRecipeRepository;
import by.itacademy.repository.entity.IngredientEntity;
import by.itacademy.repository.entity.ProductEntity;
import by.itacademy.repository.entity.RecipeEntity;
import by.itacademy.service.api.IProductService;
import by.itacademy.service.api.IRecipeService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class RecipeService implements IRecipeService {

    private final ConversionService conversionService;
    private final IRecipeRepository recipeRepository;
    private final IProductService productService;

    public RecipeService(ConversionService conversionService,
                         IRecipeRepository recipeRepository,
                         IProductService productService) {
        this.conversionService = conversionService;
        this.recipeRepository = recipeRepository;
        this.productService = productService;
    }


    @Override
    public void add(RecipeCreateDto recipe) {
        if (recipe == null) {
            throw new DtoNullPointerException("recipeCreateDto must not be null");
        }
        RecipeEntity recipeEntity = conversionService.convert(recipe, RecipeEntity.class);
        List<IngredientEntity> ingredients = getIngredients(recipe);
        recipeEntity.setProducts(ingredients);
        recipeRepository.save(recipeEntity);
    }

    @Override
    public PageDto<PageRecipeDto> getAll(Pageable pageable) {
//        if (pageable == null) {
//            throw new NullPointerException("pageable must be not null");
//        }
//        Page<UserEntity> users = adminRepository.findAll(pageable);
//        return conversionService.convert(users, PageDto.class);
        return null;
    }

    @Override
    public void update(UUID uuid, LocalDateTime dtUpdate, RecipeCreateDto recipe) {
        if (uuid == null) {
            throw new EntityNotFoundException("invalid uuid");
        }
        if (recipe == null) {
            throw new DtoNullPointerException("recipeCreateDto must not be null");
        }
        if (dtUpdate == null) {
            throw new InvalidVersionException("invalid dtUpdate");
        }
        Optional<RecipeEntity> recipeEntityOptional = recipeRepository.findById(uuid);
        RecipeEntity recipeEntity = recipeEntityOptional.orElseThrow(
                () -> new EntityNotFoundException("recipe with uuid " + uuid + " not found"));
        if (!dtUpdate.isEqual(recipeEntity.getDtUpdate())) {
            throw new InvalidVersionException("invalid dtUpdate");
        }
        update(recipeEntity, recipe);
        recipeRepository.save(recipeEntity);
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
        PageProductDto pageProductDto = productService.get(uuid);
        ProductEntity product = conversionService.convert(
                pageProductDto, ProductEntity.class);
        return new IngredientEntity(product, ingredient.getWeight());
    }
}