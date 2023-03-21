package by.itacademy.core.dto.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Objects;

public class IngredientDto implements Serializable {

    @NotNull(message = "product cannot be null")
    @Valid
    private ProductUuidDto product;

    @Positive(message = "weight must be positive")
    @NotNull(message = "weight must be positive")
    private Integer weight;

    public IngredientDto() {
    }

    public ProductUuidDto getProduct() {
        return product;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientDto that = (IngredientDto) o;
        return Objects.equals(product, that.product)
                && Objects.equals(weight, that.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, weight);
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "product=" + product +
                ", weight=" + weight +
                '}';
    }
}