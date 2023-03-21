package by.itacademy.config;

import by.itacademy.repository.api.IMailRepository;
import by.itacademy.service.api.IAdminService;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.impl.MailSenderService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class SenderServiceConfig {

    @Bean
    public ISenderService mailSenderService(
            IMailRepository mailRepository,
            IAdminService adminService,
            JavaMailSender mailSender,
            freemarker.template.Configuration freemarkerConfig,
            @Qualifier("mvcConversionService") ConversionService conversionService) {
        return new MailSenderService(
                mailRepository,
                adminService,
                mailSender,
                freemarkerConfig,
                conversionService);
    }
}