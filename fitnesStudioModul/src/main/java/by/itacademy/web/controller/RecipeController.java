package by.itacademy.web.controller;

import by.itacademy.core.Constants;
import by.itacademy.core.dto.request.RecipeCreateDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageRecipeDto;
import by.itacademy.service.api.IRecipeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/recipe")
@Validated
public class RecipeController {

    private final IRecipeService recipeService;

    public RecipeController(IRecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody @Valid RecipeCreateDto recipe) {
        this.recipeService.add(recipe);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageDto<PageRecipeDto>> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0")
            @PositiveOrZero(message = "page must be greater than or equal to 0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "20")
            @Positive(message = "size must be greater than 0") int size) {
        Pageable pageable = PageRequest.of(page, size);
        PageDto<PageRecipeDto> pageRecipes = this.recipeService.getAll(pageable);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pageRecipes);
    }

    @PutMapping(path = "{uuid}/dt_update/{dt_update}",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(
            @PathVariable(name = "uuid")
            @Pattern(regexp = Constants.UUID_PATTERN, message = "invalid uuid") String uuid,
            @PathVariable(name = "dt_update")
            @Past(message = "invalid dtUpdate") LocalDateTime dtUpdate,
            @RequestBody @Valid RecipeCreateDto recipe) {
        this.recipeService.update(UUID.fromString(uuid), dtUpdate, recipe);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}