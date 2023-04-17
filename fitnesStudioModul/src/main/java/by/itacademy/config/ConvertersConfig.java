package by.itacademy.config;

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
import by.itacademy.service.util.converter.EntityPageDtoConverter;
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
}