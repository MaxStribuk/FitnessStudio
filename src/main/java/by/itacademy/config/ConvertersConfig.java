package by.itacademy.config;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.dto.request.UserRegistrarionDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.dao.entity.UserEntity;
import by.itacademy.service.api.IConverter;
import by.itacademy.service.converters.UserCreateDtoEntityConverter;
import by.itacademy.service.converters.UserRegistrarionDtoEntityConverter;
import by.itacademy.web.converters.EntityPageDtoConverter;
import by.itacademy.web.converters.UserEntityPageDtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ConvertersConfig {

    @Bean
    public Converter<UserCreateDto, UserEntity> userCreateDtoEntityConverter() {
        return new UserCreateDtoEntityConverter();
    }

    @Bean
    public Converter<UserRegistrarionDto, UserEntity> userRegistrarionDtoEntityConverter() {
        return new UserRegistrarionDtoEntityConverter();
    }

    @Bean
    public IConverter<UserEntity, PageUserDto> entityPageDtoConverter() {
        return new EntityPageDtoConverter();
    }

    @Bean Converter<UserEntity, PageUserDto> userEntityPageDtoConverter() {
        return new UserEntityPageDtoConverter();
    }
}