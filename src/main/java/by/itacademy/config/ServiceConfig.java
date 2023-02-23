package by.itacademy.config;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.dto.request.UserRegistrarionDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.dao.api.IAdminRepository;
import by.itacademy.dao.api.IUserRepository;
import by.itacademy.dao.entity.UserEntity;
import by.itacademy.service.api.IAdminService;
import by.itacademy.service.api.IConverter;
import by.itacademy.service.api.IUserService;
import by.itacademy.service.impl.AdminService;
import by.itacademy.service.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class ServiceConfig {

    @Bean
    public IAdminService adminService(IAdminRepository adminRepository,
                                      Converter<UserCreateDto, UserEntity> userCreateDtoEntityConverter,
                                      Converter<UserEntity, PageUserDto> userEntityPageDtoConverter,
                                      IConverter<UserEntity, PageUserDto> entityPageDtoConverter) {
        return new AdminService(adminRepository,
                userCreateDtoEntityConverter,
                userEntityPageDtoConverter,
                entityPageDtoConverter);
    }

    @Bean
    public IUserService userService(IUserRepository userRepository,
                                    Converter<UserRegistrarionDto, UserEntity> userRegistrarionDtoEntityConverter) {
        return new UserService(userRepository,
                userRegistrarionDtoEntityConverter);
    }
}