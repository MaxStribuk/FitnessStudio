package by.itacademy.core.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class RecipeCreateDto implements Serializable {

    @NotBlank(message = "title cannot be empty")
    private String title;

    @NotNull(message = "composition cannot be null")
    @Size(min = 1, message = "the composition must contain at least one product")
    @Valid
    private List<IngredientDto> composition;

    public RecipeCreateDto() {
    }

    public String getTitle() {
        return title;
    }

    public List<IngredientDto> getComposition() {
        return composition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeCreateDto that = (RecipeCreateDto) o;
        return Objects.equals(title, that.title)
                && Objects.equals(composition, that.composition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, composition);
    }

    @Override
    public String toString() {
        return "RecipeCreateDto{" +
                "title='" + title + '\'' +
                ", composition=" + composition +
                '}';
    }
}