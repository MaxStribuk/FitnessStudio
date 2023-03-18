package by.itacademy.core.dto.response;

import by.itacademy.web.util.serializer.DoubleSerializer;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"product", "weight", "calories",
        "proteins", "fats", "carbohydrates"})
public class IngredientDto implements Serializable {

    private PageProductDto product;

    private int weight;

    private int calories;

    @JsonSerialize(using = DoubleSerializer.class)
    private double proteins;

    @JsonSerialize(using = DoubleSerializer.class)
    private double fats;

    @JsonSerialize(using = DoubleSerializer.class)
    private double carbohydrates;

    public IngredientDto() {
    }

    public IngredientDto(PageProductDto product, int weight, int calories,
                         double proteins, double fats, double carbohydrates) {
        this.product = product;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public PageProductDto getProduct() {
        return product;
    }

    public void setProduct(PageProductDto product) {
        this.product = product;
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
        IngredientDto that = (IngredientDto) o;
        return weight == that.weight
                && calories == that.calories
                && Double.compare(that.proteins, proteins) == 0
                && Double.compare(that.fats, fats) == 0
                && Double.compare(that.carbohydrates, carbohydrates) == 0
                && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, weight,
                calories, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "IngredientDto{" +
                "product=" + product +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}