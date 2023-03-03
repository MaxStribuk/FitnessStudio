package by.itacademy.config;

import by.itacademy.repository.api.IAdminRepository;
import by.itacademy.service.api.IAdminService;
import by.itacademy.service.impl.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class AdminServiceConfig {

    @Bean
    public IAdminService adminService(IAdminRepository adminRepository,
                                      ConversionService conversionService) {
        return new AdminService(adminRepository, conversionService);
    }
}