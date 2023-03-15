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
import by.itacademy.service.util.converter.EntityPageDtoConverter;
import by.itacademy.service.util.converter.PageProductDtoEntityConverter;
import by.itacademy.service.util.converter.ProductCreateDtoEntityConverter;
import by.itacademy.service.util.converter.ProductEntityPageDtoConverter;
import by.itacademy.service.util.converter.RecipeCreateDtoEntityConverter;
import by.itacademy.service.util.converter.RecipeEntityPageDtoConverter;
import by.itacademy.service.util.converter.UserCreateDtoEntityConverter;
import by.itacademy.service.util.converter.UserEntityCreationDtoConverter;
import by.itacademy.service.util.converter.UserEntityMailEntityConverter;
import by.itacademy.service.util.converter.UserEntityPageDtoConverter;
import by.itacademy.service.util.converter.UserEntityUserDetailsConverter;
import by.itacademy.service.util.converter.UserRegistrarionDtoEntityConverter;
import by.itacademy.web.util.converter.StringLocalDateTimeConverter;
import by.itacademy.web.util.converter.StringUuidConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

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

    @Bean
    public Converter<UserEntity, UserDetails> userEntityUserDetailsConverter() {
        return new UserEntityUserDetailsConverter();
    }
}