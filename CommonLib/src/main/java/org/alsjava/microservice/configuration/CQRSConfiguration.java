package org.alsjava.microservice.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.MessageChannel;

@ConditionalOnProperty(prefix = "pattern", name = "cqrs.enabled", havingValue = "true")
@Configuration
public class CQRSConfiguration {

    @Bean
    public MessagingTemplate messagingTemplate() {
        return new MessagingTemplate();
    }

    @Bean
    public MessageChannel commandChannel() {
        return new PublishSubscribeChannel();
    }
}
