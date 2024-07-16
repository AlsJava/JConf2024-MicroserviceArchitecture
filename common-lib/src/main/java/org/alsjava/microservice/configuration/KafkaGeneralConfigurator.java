package org.alsjava.microservice.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConditionalOnProperty(prefix = "kafka", name = "bootstrap-servers")
public class KafkaGeneralConfigurator {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value("${kafka.trust-package}")
    private String trustPackage;

    @Value("${kafka.timeoout}")
    private long timeout;

    // TODO implement default methods

    /**
     * Default Consumer
     *
     * @param groupId Group to listen
     * @return Consumer configuration
     */
    public Map<String, Object> consumerConfigs(String groupId) {
        return consumerJSONConfigs(groupId);
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

    public Map<String, Object> consumerJSONConfigs(String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
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
}
