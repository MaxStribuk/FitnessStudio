package by.itacademy.service.util.converter;

import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.repository.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PageProductDtoEntityConverter implements Converter<PageProductDto, ProductEntity> {

    @Override
    public ProductEntity convert(PageProductDto product) {
        return new ProductEntity(
                product.getUuid(),
                product.getDtCreate(),
                product.getDtUpdate(),
                product.getTitle(),
                product.getWeight(),
                product.getCalories(),
                product.getProteins(),
                product.getFats(),
                product.getCarbohydrates()
        );
    }
}