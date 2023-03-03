package by.itacademy.config;

import by.itacademy.repository.api.IProductRepository;
import by.itacademy.service.api.IProductService;
import by.itacademy.service.impl.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class ProductServiceConfig {

    @Bean
    public IProductService productService(IProductRepository productRepository,
                                          ConversionService conversionService) {
        return new ProductService(productRepository, conversionService);
    }
}