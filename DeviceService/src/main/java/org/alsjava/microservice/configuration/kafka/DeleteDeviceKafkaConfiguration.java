package org.alsjava.microservice.configuration.kafka;

import lombok.RequiredArgsConstructor;
import org.alsjava.microservice.configuration.KafkaGeneralConfigurator;
import org.alsjava.microservice.model.request.DeleteDeviceRequest;
import org.alsjava.microservice.model.response.DeleteDeviceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DefaultErrorHandler;

@RequiredArgsConstructor
@Configuration
public class DeleteDeviceKafkaConfiguration {

    private final KafkaGeneralConfigurator kafkaGeneralConfigurator;
    private final DefaultErrorHandler kafkaErrorHandler;

    @Value("${kafka.devices.delete.group}")
    private String groupId;

    @Bean
    public KafkaTemplate<String, DeleteDeviceResponse> deleteDeviceKafkaTemplate(ProducerFactory<String, DeleteDeviceResponse> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, DeleteDeviceRequest> deleteDeviceKafkaListenerContainerFactory(ConsumerFactory<String, DeleteDeviceRequest> deleteDeviceConsumerFactory) {
        return kafkaGeneralConfigurator.concurrentKafkaListenerContainerFactory(deleteDeviceConsumerFactory, kafkaErrorHandler);
    }

    @Bean
    public ProducerFactory<String, DeleteDeviceResponse> deleteDeviceProducerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaGeneralConfigurator.producerConfigs());
    }

    @Bean
    public ConsumerFactory<String, DeleteDeviceRequest> deleteDeviceConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaGeneralConfigurator.consumerConfigs(groupId, DeleteDeviceRequest.class));
    }

}
