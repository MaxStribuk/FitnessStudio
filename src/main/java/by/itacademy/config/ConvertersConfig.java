package by.itacademy.config;

import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.dto.request.UserRegistrationDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.ProductEntity;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.util.converters.EntityPageDtoConverter;
import by.itacademy.service.util.converters.ProductCreateDtoEntityConverter;
import by.itacademy.service.util.converters.ProductEntityPageDtoConverter;
import by.itacademy.service.util.converters.StringLocalDateTimeConverter;
import by.itacademy.service.util.converters.UserCreateDtoEntityConverter;
import by.itacademy.service.util.converters.UserEntityCreationDtoConverter;
import by.itacademy.service.util.converters.UserEntityMailEntityConverter;
import by.itacademy.service.util.converters.UserRegistrarionDtoEntityConverter;
import by.itacademy.service.util.converters.UserEntityPageDtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Configuration
public class ConvertersConfig  {

    @Bean
    public Converter<Page<UserEntity>, PageDto<PageUserDto>> userEntityPageDtoConverter(
            Converter<UserEntity, PageUserDto> converter) {
        return new EntityPageDtoConverter<>(converter);
    }

    @Bean
    public Converter<Page<ProductEntity>, PageDto<PageProductDto>> productEntityPageDtoConverter(
            Converter<ProductEntity, PageProductDto> converter) {
        return new EntityPageDtoConverter<>(converter);
    }

    @Bean
    public Converter<UserEntity, PageUserDto> userEntityPageDtoConverter() {
        return new UserEntityPageDtoConverter();
    }

    @Bean
    public Converter<UserCreateDto, UserEntity> userCreateDtoEntityConverter() {
        return new UserCreateDtoEntityConverter();
    }

    @Bean
    public Converter<UserRegistrationDto, UserEntity> userRegistrarionDtoEntityConverter() {
        return new UserRegistrarionDtoEntityConverter();
    }

    @Bean
    public Converter<UserEntity, MailEntity> userEntityMailEntityConverter() {
        return new UserEntityMailEntityConverter();
    }

    @Bean
    public Converter<UserEntity, UserCreateDto> userEntityCreationDtoConverter() {
        return new UserEntityCreationDtoConverter();
    }

    @Bean
    public Converter<ProductCreateDto, ProductEntity> productCreateDtoEntityConverter() {
        return new ProductCreateDtoEntityConverter();
    }

    @Bean
    public Converter<ProductEntity, PageProductDto> productEntityPageProductDtoConverter() {
        return new ProductEntityPageDtoConverter();
    }

    @Bean
    public Converter<String, LocalDateTime> stringLocalDateTimeConverter() {
        return new StringLocalDateTimeConverter();
    }
}