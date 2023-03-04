package by.itacademy.core.dto.request;

import by.itacademy.service.util.validators.Uuid;
import by.itacademy.web.util.deserializers.UuidDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class ProductUuidDto implements Serializable {

    @Uuid
    @JsonDeserialize(using = UuidDeserializer.class)
    private UUID uuid;

    public ProductUuidDto() {
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductUuidDto that = (ProductUuidDto) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "ProductUuidDto{" +
                "Uuid=" + uuid +
                '}';
    }
}