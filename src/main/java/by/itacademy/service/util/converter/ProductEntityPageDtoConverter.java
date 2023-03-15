package by.itacademy.service.util.converter;

import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.repository.entity.ProductEntity;
import org.springframework.core.convert.converter.Converter;

public class ProductEntityPageDtoConverter implements Converter<ProductEntity, PageProductDto> {

    @Override
    public PageProductDto convert(ProductEntity product) {
        return new PageProductDto(
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