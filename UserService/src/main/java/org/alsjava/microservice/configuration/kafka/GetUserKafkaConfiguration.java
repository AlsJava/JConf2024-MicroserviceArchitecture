package org.alsjava.microservice.configuration.kafka;

import lombok.RequiredArgsConstructor;
import org.alsjava.microservice.configuration.KafkaGeneralConfigurator;
import org.alsjava.microservice.model.request.GetUserRequest;
import org.alsjava.microservice.model.response.GetUserResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DefaultErrorHandler;

@RequiredArgsConstructor
@Configuration
public class GetUserKafkaConfiguration {

    private final KafkaGeneralConfigurator kafkaGeneralConfigurator;
    private final DefaultErrorHandler kafkaErrorHandler;

    @Value("${kafka.users.get.group}")
    private String groupId;

    @Bean
    public KafkaTemplate<String, GetUserResponse> getUserKafkaTemplate() {
        return new KafkaTemplate<>(getUserProducerFactory());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GetUserRequest> getUserKafkaListenerContainerFactory(ConsumerFactory<String, GetUserRequest> getUserConsumerFactory) {
        return kafkaGeneralConfigurator.concurrentKafkaListenerContainerFactory(getUserConsumerFactory, kafkaErrorHandler);
    }

    @Bean
    public ProducerFactory<String, GetUserResponse> getUserProducerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaGeneralConfigurator.producerConfigs());
    }

    @Bean
    public ConsumerFactory<String, GetUserRequest> getUserConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaGeneralConfigurator.consumerConfigs(groupId, GetUserResponse.class));
    }

}
