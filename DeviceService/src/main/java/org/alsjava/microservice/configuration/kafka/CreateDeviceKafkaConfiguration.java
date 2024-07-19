package org.alsjava.microservice.configuration.kafka;

import lombok.RequiredArgsConstructor;
import org.alsjava.microservice.configuration.KafkaGeneralConfigurator;
import org.alsjava.microservice.model.request.CreateDeviceRequest;
import org.alsjava.microservice.model.response.CreateDeviceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DefaultErrorHandler;

@RequiredArgsConstructor
@Configuration
public class CreateDeviceKafkaConfiguration {

    private final KafkaGeneralConfigurator kafkaGeneralConfigurator;
    private final DefaultErrorHandler kafkaErrorHandler;

    @Value("${kafka.devices.create.group}")
    private String groupId;

    @Bean
    public KafkaTemplate<String, CreateDeviceResponse> createDeviceKafkaTemplate() {
        return new KafkaTemplate<>(createDeviceProducerFactory());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateDeviceRequest> createDeviceKafkaListenerContainerFactory(ConsumerFactory<String, CreateDeviceRequest> createDeviceConsumerFactory) {
        return kafkaGeneralConfigurator.concurrentKafkaListenerContainerFactory(createDeviceConsumerFactory, kafkaErrorHandler);
    }

    @Bean
    public ProducerFactory<String, CreateDeviceResponse> createDeviceProducerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaGeneralConfigurator.producerConfigs());
    }

    @Bean
    public ConsumerFactory<String, CreateDeviceRequest> createDeviceConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaGeneralConfigurator.consumerConfigs(groupId, CreateDeviceRequest.class));
    }

}
