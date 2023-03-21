package by.itacademy.config;

import by.itacademy.core.dto.response.PageDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.api.IAdminRepository;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.api.IAdminService;
import by.itacademy.service.impl.AdminService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminServiceConfig {

    @Bean
    public IAdminService adminService(
            IAdminRepository adminRepository,
            @Qualifier("mvcConversionService") ConversionService conversionService,
            Converter<Page<UserEntity>, PageDto<PageUserDto>> userPageDtoConverter,
            PasswordEncoder encoder) {
        return new AdminService(adminRepository,
                conversionService,
                userPageDtoConverter,
                encoder);
    }
}