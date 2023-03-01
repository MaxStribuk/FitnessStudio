package by.itacademy.config;

import by.itacademy.repository.api.IMailRepository;
import by.itacademy.service.api.IAdminService;
import by.itacademy.service.api.ISenderService;
import by.itacademy.service.impl.MailSenderService;
import by.itacademy.web.listeners.MailSendLoaderListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class SenderServiceConfig {

    private static final int EXECUTOR_CORE_POOL_SIZE = 4;

    @Bean
    public ISenderService mailSenderService(
            IMailRepository mailRepository,
            IAdminService adminService,
            JavaMailSender mailSender,
            freemarker.template.Configuration freemarkerConfig,
            ScheduledExecutorService executorService,
            ConversionService conversionService) {
        return new MailSenderService(
                mailRepository,
                adminService,
                mailSender,
                freemarkerConfig,
                executorService,
                conversionService);
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