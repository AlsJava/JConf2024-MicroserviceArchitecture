package org.alsjava.microservice.configuration.kafka;

import lombok.RequiredArgsConstructor;
import org.alsjava.microservice.configuration.KafkaGeneralConfigurator;
import org.alsjava.microservice.model.request.CreateDeviceRequest;
import org.alsjava.microservice.model.response.CreateDeviceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@RequiredArgsConstructor
@Configuration
public class CreateDeviceKafkaConfiguration {

    private final KafkaGeneralConfigurator kafkaGeneralConfigurator;
    private final DefaultErrorHandler kafkaErrorHandler;

    @Value("${kafka.devices.create.topics.response}")
    private String createDeviceResponseTopic;
    @Value("${kafka.devices.create.group}")
    private String groupId;

    @Bean
    public ReplyingKafkaTemplate<String, CreateDeviceRequest, CreateDeviceResponse> createDeviceReplyingTemplate(ProducerFactory<String, CreateDeviceRequest> createDeviceProducerFactory,
                                                                                                                 ConcurrentMessageListenerContainer<String, CreateDeviceResponse> createDeviceRepliesContainer) {
        return kafkaGeneralConfigurator.replyingKafkaTemplate(createDeviceProducerFactory, createDeviceRepliesContainer);
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, CreateDeviceResponse> createDeviceRepliesContainer(ConcurrentKafkaListenerContainerFactory<String, CreateDeviceResponse> createDeviceKafkaListenerContainerFactory) {
        return kafkaGeneralConfigurator.concurrentMessageListenerContainer(createDeviceResponseTopic, groupId, createDeviceKafkaListenerContainerFactory);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateDeviceResponse> createDeviceKafkaListenerContainerFactory(ConsumerFactory<String, CreateDeviceResponse> createDeviceConsumerFactory) {
        return kafkaGeneralConfigurator.concurrentKafkaListenerContainerFactory(createDeviceConsumerFactory, kafkaErrorHandler);
    }

    @Bean
    public ProducerFactory<String, CreateDeviceRequest> createDeviceProducerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaGeneralConfigurator.producerConfigs());
    }

    @Bean
    public ConsumerFactory<String, CreateDeviceResponse> createDeviceConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(kafkaGeneralConfigurator.consumerConfigs(groupId, CreateDeviceResponse.class));
    }

}
