package by.itacademy.config;

import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.repository.api.IProductRepository;
import by.itacademy.repository.entity.ProductEntity;
import by.itacademy.service.api.IConverter;
import by.itacademy.service.api.IProductService;
import by.itacademy.service.impl.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ProductServiceConfig {

    @Bean
    public IProductService productService(
            IProductRepository productRepository,
            ConversionService conversionService,
            Converter<ProductEntity, PageProductDto> productEntityPageDtoConverter,
            IConverter<ProductEntity, PageProductDto> entityPageDtoConverter) {
        return new ProductService(productRepository,
                conversionService, productEntityPageDtoConverter, entityPageDtoConverter);
    }
}