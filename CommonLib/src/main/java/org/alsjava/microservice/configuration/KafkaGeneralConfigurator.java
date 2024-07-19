package org.alsjava.microservice.configuration;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
@ConditionalOnProperty(prefix = "kafka", name = "enabled", havingValue = "true")
public class KafkaGeneralConfigurator {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${kafka.trust-package}")
    private String trustPackage;

    @Value("${kafka.timeout}")
    private long timeout;

    /**
     * Default Consumer
     *
     * @param groupId Group to listen
     * @return Consumer configuration
     */
    public <T> Map<String, Object> consumerConfigs(String groupId, Class<T> defaultType) {
        return consumerJSONConfigs(groupId, defaultType);
    }

    /**
     * Default Producer
     *
     * @return Producer Configuration
     */
    public Map<String, Object> producerConfigs() {
        return producerJSONConfigs();
    }

    // TODO implement a parser to improve performance (Protobuff, BJson, or other)

    public <T> Map<String, Object> consumerJSONConfigs(String groupId, Class<T> defaultType) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, defaultType.getCanonicalName());
        props.put(JsonDeserializer.TRUSTED_PACKAGES, trustPackage);
        return props;
    }

    public Map<String, Object> producerJSONConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configProps;
    }

    public <T> ConcurrentKafkaListenerContainerFactory<String, T> concurrentKafkaListenerContainerFactory(ConsumerFactory<String, T> consumerFactory, DefaultErrorHandler kafkaErrorHandler) {
        ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setCommonErrorHandler(kafkaErrorHandler);
        factory.setConsumerFactory(consumerFactory);
        factory.setMissingTopicsFatal(true);
        return factory;
    }

    public <T, K> ReplyingKafkaTemplate<String, T, K> replyingKafkaTemplate(ProducerFactory<String, T> createDeviceProducerFactory,
                                                                            ConcurrentMessageListenerContainer<String, K> createDeviceRepliesContainer) {
        ReplyingKafkaTemplate<String, T, K> replyTemplate = new ReplyingKafkaTemplate<>(createDeviceProducerFactory, createDeviceRepliesContainer);
        replyTemplate.setDefaultReplyTimeout(Duration.ofSeconds(timeout));
        replyTemplate.setSharedReplyTopic(true);
        return replyTemplate;
    }

    public <T> ConcurrentMessageListenerContainer<String, T> concurrentMessageListenerContainer(String topic, String groupId, ConcurrentKafkaListenerContainerFactory<String, T> createDeviceKafkaListenerContainerFactory) {
        ConcurrentMessageListenerContainer<String, T> repliesContainer = createDeviceKafkaListenerContainerFactory.createContainer(topic);
        repliesContainer.getContainerProperties().setMissingTopicsFatal(false);
        repliesContainer.getContainerProperties().setGroupId(groupId);
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
}
