package by.itacademy.config;

import by.itacademy.core.dto.request.UserRegistrarionDto;
import by.itacademy.repository.api.IUserRepository;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.api.IUserService;
import by.itacademy.service.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

@Configuration
public class UserServiceConfig {

    @Bean
    public IUserService userService(
            IUserRepository userRepository,
            Converter<UserRegistrarionDto, UserEntity> userRegistrarionDtoEntityConverter,
            ISenderService senderService) {
        return new UserService(userRepository,
                userRegistrarionDtoEntityConverter,
                senderService);
    }
}