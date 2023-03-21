package by.itacademy.config;

import by.itacademy.repository.api.IUserRepository;
import by.itacademy.service.api.IJwtTokenUtil;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.api.IUserService;
import by.itacademy.service.impl.UserService;
import by.itacademy.service.util.UserHolder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserServiceConfig {

    @Bean
    public IUserService userService(
            IUserRepository userRepository,
            ISenderService senderService,
            @Qualifier("mvcConversionService") ConversionService conversionService,
            PasswordEncoder encoder,
            UserHolder holder,
            IJwtTokenUtil jwtTokenUtil) {
        return new UserService(userRepository,
                senderService,
                conversionService,
                encoder,
                holder,
                jwtTokenUtil);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}