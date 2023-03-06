package by.itacademy.repository.entity;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "ingredients")
public class IngredientEntity {

    @Id
    @GeneratedValue()
    @Type(type = "pg-uuid")
    @Column(name = "uuid", nullable = false, length = 36)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    @Column(name = "weight", nullable = false)
    private int weight;

    public IngredientEntity() {
    }

    public IngredientEntity(ProductEntity product, int weight) {
        this.product = product;
        this.weight = weight;
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
        return "ingredients{" +
                "product=" + product +
                ", weight=" + weight +
                '}';
    }
}