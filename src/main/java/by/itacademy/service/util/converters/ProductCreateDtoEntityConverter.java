package by.itacademy.service.util.converters;

import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.repository.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProductCreateDtoEntityConverter implements Converter<ProductCreateDto, ProductEntity> {

    @Override
    public ProductEntity convert(ProductCreateDto product) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return new ProductEntity(
                UUID.randomUUID(),
                currentDateTime,
                currentDateTime,
                product.getTitle(),
                product.getWeight(),
                product.getCalories(),
                product.getProteins(),
                product.getFats(),
                product.getCarbohydrates()
        );
    }
}
