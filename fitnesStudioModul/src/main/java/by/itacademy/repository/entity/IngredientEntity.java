package by.itacademy.repository.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Embeddable
public class IngredientEntity {

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "weight", nullable = false)
    private int weight;

    public IngredientEntity() {
    }

    public IngredientEntity(ProductEntity product, int weight) {
        this.product = product;
        this.weight = weight;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientEntity that = (IngredientEntity) o;
        return weight == that.weight
                && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, weight);
    }

    @Override
    public String toString() {
        return "IngredientEntity{" +
                "product=" + product +
                ", weight=" + weight +
                '}';
    }
}