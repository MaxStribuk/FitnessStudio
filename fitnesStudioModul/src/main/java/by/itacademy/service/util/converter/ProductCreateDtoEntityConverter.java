package by.itacademy.service.util.converter;

import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.repository.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProductCreateDtoEntityConverter implements Converter<ProductCreateDto, ProductEntity> {

    @Override
    public ProductEntity convert(ProductCreateDto product) {
        return new ProductEntity(
                product.getTitle(),
                product.getWeight(),
                product.getCalories(),
                product.getProteins(),
                product.getFats(),
                product.getCarbohydrates()
        );
    }
}
