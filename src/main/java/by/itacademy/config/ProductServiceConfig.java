package by.itacademy.config;

import by.itacademy.service.api.IProductService;
import by.itacademy.service.impl.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductServiceConfig {

    @Bean
    public IProductService productService() {
        return new ProductService();
    }
}