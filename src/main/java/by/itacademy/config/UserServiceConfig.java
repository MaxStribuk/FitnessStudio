package by.itacademy.config;

import by.itacademy.repository.api.IUserRepository;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.api.IUserService;
import by.itacademy.service.impl.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class UserServiceConfig {

    @Bean
    public IUserService userService(
            IUserRepository userRepository,
            ISenderService senderService,
            ConversionService conversionService) {
        return new UserService(userRepository,
                senderService,
                conversionService);
    }
}