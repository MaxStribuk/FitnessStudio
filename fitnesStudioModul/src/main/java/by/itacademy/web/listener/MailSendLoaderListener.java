package by.itacademy.web.listener;

import by.itacademy.service.api.ISenderService;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class MailSendLoaderListener {

    private final ISenderService senderService;

    public MailSendLoaderListener(ISenderService senderService) {
        this.senderService = senderService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void contextInitialized() {
        senderService.initialize();
    }

    @EventListener(ContextClosedEvent.class)
    public void destroy() {
        senderService.stop();
    }
}