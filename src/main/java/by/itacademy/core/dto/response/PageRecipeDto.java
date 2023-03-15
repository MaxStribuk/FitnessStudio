package by.itacademy.core.dto.response;

import by.itacademy.web.util.serializer.DateSerializer;
import by.itacademy.web.util.serializer.DoubleSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@JsonPropertyOrder({"uuid", "dt_create", "dt_update", "title", "products",
        "weight", "calories", "proteins", "fats", "carbohydrates"})
public class PageRecipeDto implements Serializable {

    private UUID uuid;

    @JsonSerialize(using = DateSerializer.class)
    @JsonProperty("dt_create")
    private LocalDateTime dtCreate;

    @JsonSerialize(using = DateSerializer.class)
    @JsonProperty("dt_update")
    private LocalDateTime dtUpdate;

    private String title;

    private List<IngredientDto> products;

    private int weight;

    private int calories;

    @JsonSerialize(using = DoubleSerializer.class)
    private double proteins;

    @JsonSerialize(using = DoubleSerializer.class)
    private double fats;

    @JsonSerialize(using = DoubleSerializer.class)
    private double carbohydrates;

    public PageRecipeDto() {
    }

    public PageRecipeDto(UUID uuid, LocalDateTime dtCreate, LocalDateTime dtUpdate,
                         String title, List<IngredientDto> products, int weight,
                         int calories, double proteins, double fats, double carbohydrates) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.title = title;
        this.products = products;
        this.weight = weight;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.carbohydrates = carbohydrates;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<IngredientDto> getProducts() {
        return products;
    }

    public void setProducts(List<IngredientDto> products) {
        this.products = products;
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
        PageRecipeDto that = (PageRecipeDto) o;
        return weight == that.weight
                && calories == that.calories
                && Double.compare(that.proteins, proteins) == 0
                && Double.compare(that.fats, fats) == 0
                && Double.compare(that.carbohydrates, carbohydrates) == 0
                && Objects.equals(uuid, that.uuid) && Objects.equals(dtCreate, that.dtCreate)
                && Objects.equals(dtUpdate, that.dtUpdate) && Objects.equals(title, that.title)
                && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, title,
                products, weight, calories, proteins, fats, carbohydrates);
    }

    @Override
    public String toString() {
        return "PageRecipeDto{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", title='" + title + '\'' +
                ", products=" + products +
                ", weight=" + weight +
                ", calories=" + calories +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }
}