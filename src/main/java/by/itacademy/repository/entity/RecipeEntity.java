package by.itacademy.repository.entity;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(schema = "app", name = "recipes")
public class RecipeEntity implements Serializable {

    @Id
    @GeneratedValue()
    @Type(type = "pg-uuid")
    @Column(name = "uuid", nullable = false, length = 36)
    private UUID uuid;

    @Column(name = "dt_create", nullable = false)
    @CreationTimestamp
    private LocalDateTime dtCreate;

    @Column(name = "dt_update", nullable = false)
    @Version
    private LocalDateTime dtUpdate;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @ElementCollection
    @CollectionTable(schema = "app", name = "recipes_products",
            joinColumns = @JoinColumn(name = "recipe_id"))
    private List<IngredientEntity> products;

    public RecipeEntity() {
    }

    public RecipeEntity(String title) {
        this.title = title;
        this.products = new ArrayList<>();
    }

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public String getTitle() {
        return title;
    }

    public List<IngredientEntity> getProducts() {
        return products;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProducts(List<IngredientEntity> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeEntity that = (RecipeEntity) o;
        return Objects.equals(uuid, that.uuid)
                && Objects.equals(dtCreate, that.dtCreate)
                && Objects.equals(dtUpdate, that.dtUpdate)
                && Objects.equals(title, that.title)
                && Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, dtCreate, dtUpdate, title, products);
    }

    @Override
    public String toString() {
        return "RecipeEntity{" +
                "uuid=" + uuid +
                ", dtCreate=" + dtCreate +
                ", dtUpdate=" + dtUpdate +
                ", title='" + title + '\'' +
                ", products=" + products +
                '}';
    }
}