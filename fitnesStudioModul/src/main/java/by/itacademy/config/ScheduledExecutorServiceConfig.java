package by.itacademy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class ScheduledExecutorServiceConfig {

    private static final int EXECUTOR_CORE_POOL_SIZE = 4;

    @Bean
    public ScheduledExecutorService executorService() {
        return Executors.newScheduledThreadPool(EXECUTOR_CORE_POOL_SIZE);
    }
}