package by.itacademy.config;

import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.core.dto.request.RecipeCreateDto;
import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.dto.request.UserRegistrationDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.core.dto.response.PageRecipeDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.ProductEntity;
import by.itacademy.repository.entity.RecipeEntity;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.util.converters.EntityPageDtoConverter;
import by.itacademy.service.util.converters.PageProductDtoEntityConverter;
import by.itacademy.service.util.converters.ProductCreateDtoEntityConverter;
import by.itacademy.service.util.converters.ProductEntityPageDtoConverter;
import by.itacademy.service.util.converters.RecipeCreateDtoEntityConverter;
import by.itacademy.service.util.converters.RecipeEntityPageDtoConverter;
import by.itacademy.web.util.converters.StringLocalDateTimeConverter;
import by.itacademy.web.util.converters.StringUuidConverter;
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
import java.util.UUID;

@Configuration
public class ConvertersConfig  {

    @Bean
    public Converter<Page<UserEntity>, PageDto<PageUserDto>> userEntityPagesDtoConverter(
            Converter<UserEntity, PageUserDto> converter) {
        return new EntityPageDtoConverter<>(converter);
    }

    @Bean
    public Converter<Page<ProductEntity>, PageDto<PageProductDto>> productEntityPageDtoConverter(
            Converter<ProductEntity, PageProductDto> converter) {
        return new EntityPageDtoConverter<>(converter);
    }

    @Bean
    public Converter<Page<RecipeEntity>, PageDto<PageRecipeDto>> recipeEntityPageDtoConverter(
            Converter<RecipeEntity, PageRecipeDto> converter) {
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

    @Bean
    public Converter<String, UUID> stringUuidConverter() {
        return new StringUuidConverter();
    }

    @Bean
    public Converter<PageProductDto, ProductEntity> pageProductDtoEntityConverter() {
        return new PageProductDtoEntityConverter();
    }

    @Bean
    public Converter<RecipeCreateDto, RecipeEntity> recipeCreateDtoEntityConverter() {
        return new RecipeCreateDtoEntityConverter();
    }

    @Bean
    public Converter<RecipeEntity, PageRecipeDto> recipeEntityPageRecipeDtoConverter(
            Converter<ProductEntity, PageProductDto> converter) {
        return new RecipeEntityPageDtoConverter(converter);
    }
}