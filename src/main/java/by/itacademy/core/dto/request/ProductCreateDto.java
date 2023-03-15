package by.itacademy.core.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Objects;

public class ProductCreateDto implements Serializable {

    @NotBlank(message = "title cannot be empty")
    private String title;

    @Positive(message = "weight must be greater than 0")
    @NotNull(message = "weight cannot be empty")
    private Integer weight;

    @PositiveOrZero(message = "weight must be greater than or equal to 0")
    @NotNull(message = "calories cannot be empty")
    private Integer calories;

    @PositiveOrZero(message = "weight must be greater than or equal to 0")
    @NotNull(message = "proteins cannot be empty")
    private Double proteins;

    @PositiveOrZero(message = "weight must be greater than or equal to 0")
    @NotNull(message = "fats cannot be empty")
    private Double fats;

    @PositiveOrZero(message = "weight must be greater than or equal to 0")
    @NotNull(message = "carbohydrates cannot be empty")
    private Double carbohydrates;

    public ProductCreateDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public double getProteins() {
        return proteins;
    }

    public void setProteins(double proteins) {
        this.proteins = proteins;
    }

    public double getFats() {
        return fats;
    }

    public void setFats(double fats) {
        this.fats = fats;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCreateDto that = (ProductCreateDto) o;
        return Objects.equals(title, that.title)
                && Objects.equals(weight, that.weight)
                && Objects.equals(calories, that.calories)
                && Objects.equals(proteins, that.proteins)
                && Objects.equals(fats, that.fats)
                && Objects.equals(carbohydrates, that.carbohydrates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, weight, calories, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "ProductCreateDto{" +
                "title='" + title + '\'' +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}