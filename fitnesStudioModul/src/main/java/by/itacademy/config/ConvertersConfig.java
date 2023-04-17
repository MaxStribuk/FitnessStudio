package by.itacademy.config;

import by.itacademy.core.dto.request.AuditCreateDto;
import by.itacademy.core.dto.request.ProductCreateDto;
import by.itacademy.core.dto.request.RecipeCreateDto;
import by.itacademy.core.dto.request.ReportCreateDto;
import by.itacademy.core.dto.response.PageAuditDto;
import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageProductDto;
import by.itacademy.core.dto.response.PageRecipeDto;
import by.itacademy.core.dto.response.PageReportDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.entity.AuditEntity;
import by.itacademy.repository.entity.ProductEntity;
import by.itacademy.repository.entity.RecipeEntity;
import by.itacademy.repository.entity.ReportEntity;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.util.converter.AuditCreateDtoEntityConverter;
import by.itacademy.service.util.converter.AuditEntityPageDtoConverter;
import by.itacademy.service.util.converter.EntityPageDtoConverter;
import by.itacademy.service.util.converter.PageProductDtoEntityConverter;
import by.itacademy.service.util.converter.ProductCreateDtoEntityConverter;
import by.itacademy.service.util.converter.ProductEntityPageDtoConverter;
import by.itacademy.service.util.converter.RecipeCreateDtoEntityConverter;
import by.itacademy.service.util.converter.RecipeEntityPageDtoConverter;
import by.itacademy.service.util.converter.ReportCreateDtoEntityConverter;
import by.itacademy.service.util.converter.ReportEntityPageDtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;

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
    public Converter<Page<AuditEntity>, PageDto<PageAuditDto>> auditEntityPageDtoConverter(
            Converter<AuditEntity, PageAuditDto> converter) {
        return new EntityPageDtoConverter<>(converter);
    }

    @Bean
    public Converter<Page<ReportEntity>, PageDto<PageReportDto>> reportEntityPageDtoConverter(
            Converter<ReportEntity, PageReportDto> converter) {
        return new EntityPageDtoConverter<>(converter);
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
    public Converter<AuditEntity, PageAuditDto> auditEntityPageAuditDtoConverter() {
        return new AuditEntityPageDtoConverter();
    }

    @Bean
    public Converter<AuditCreateDto, AuditEntity> auditCreateDtoEntityConverter() {
        return new AuditCreateDtoEntityConverter();
    }

    @Bean
    public Converter<ReportCreateDto, ReportEntity> reportCreateDtoReportEntityConverter() {
        return new ReportCreateDtoEntityConverter();
    }

    @Bean
    public Converter<ReportEntity, PageReportDto> reportEntityPageReportDtoConverter() {
        return new ReportEntityPageDtoConverter();
    }
}