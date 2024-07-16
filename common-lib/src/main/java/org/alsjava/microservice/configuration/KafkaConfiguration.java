package org.alsjava.microservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.net.SocketTimeoutException;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "kafka", name = "enabled", havingValue = "true")
public class KafkaConfiguration {

    @Value(value = "${kafka.retry.interval}")
    private Long interval;

    @Value(value = "${kafka.retry.max_attempts}")
    private Long maxAttempts;

    @Bean
    public DefaultErrorHandler kafkaErrorHandler() {
        log.info("Configure kafka retry: {} intervals -- {} max attempts", interval, maxAttempts);
        BackOff fixedBackOff = new FixedBackOff(interval, maxAttempts);
        DefaultErrorHandler errorHandler = new DefaultErrorHandler((consumerRecord, exception) -> {
            // TODO logic to execute when all the retry attemps are exhausted
        }, fixedBackOff);
        errorHandler.addRetryableExceptions(SocketTimeoutException.class);
        errorHandler.addNotRetryableExceptions(NullPointerException.class);
        return errorHandler;
    }
}
