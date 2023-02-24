package by.itacademy.config;

import by.itacademy.core.dto.request.UserCreateDto;
import by.itacademy.core.dto.request.UserRegistrarionDto;
import by.itacademy.core.dto.response.PageUserDto;
import by.itacademy.repository.api.IAdminRepository;
import by.itacademy.repository.api.IMailRepository;
import by.itacademy.repository.api.IUserRepository;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.api.IAdminService;
import by.itacademy.service.api.IConverter;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.api.IUserService;
import by.itacademy.service.impl.AdminService;
import by.itacademy.service.impl.MailSenderService;
import by.itacademy.service.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ServiceConfig {

    private static final int EXECUTOR_CORE_POOL_SIZE = 4;

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
    public IUserService userService(
            IUserRepository userRepository,
            Converter<UserRegistrarionDto, UserEntity> userRegistrarionDtoEntityConverter,
            ISenderService senderService) {
        return new UserService(userRepository,
                userRegistrarionDtoEntityConverter,
                senderService);
    }

    @Bean
    public ISenderService mailSenderService(
            IMailRepository mailRepository,
            JavaMailSender mailSender,
            freemarker.template.Configuration freemarkerConfig,
            ScheduledExecutorService executorService,
            Converter<UserEntity, MailEntity> userEntityMailEntityConverter) {
        return new MailSenderService(
                mailRepository,
                mailSender,
                freemarkerConfig,
                executorService,
                userEntityMailEntityConverter);
    }

    @Bean
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(EXECUTOR_CORE_POOL_SIZE);
    }
}