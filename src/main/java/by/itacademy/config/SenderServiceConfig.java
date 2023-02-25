package by.itacademy.config;

import by.itacademy.repository.api.IMailRepository;
import by.itacademy.repository.entity.MailEntity;
import by.itacademy.repository.entity.UserEntity;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.impl.MailSenderService;
import by.itacademy.web.listeners.MailSendLoaderListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class SenderServiceConfig {

    private static final int EXECUTOR_CORE_POOL_SIZE = 4;

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
    public MailSendLoaderListener mailSendLoaderListener(ISenderService senderService) {
        return new MailSendLoaderListener(senderService);
    }

    @Bean
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(EXECUTOR_CORE_POOL_SIZE);
    }
}