package by.itacademy.config;

import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.api.IAdminRepository;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.api.IAdminService;
import by.itacademy.service.api.IConverter;
import by.itacademy.service.impl.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class AdminServiceConfig {

    @Bean
    public IAdminService adminService(
            IAdminRepository adminRepository,
            Converter<UserEntity, PageUserDto> userEntityPageDtoConverter,
            IConverter<UserEntity, PageUserDto> entityPagesDtoConverter,
            ConversionService conversionService) {
        return new AdminService(adminRepository,
                userEntityPageDtoConverter,
                entityPagesDtoConverter,
                conversionService);
    }
}