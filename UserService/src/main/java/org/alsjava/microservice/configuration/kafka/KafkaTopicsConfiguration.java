package org.alsjava.microservice.configuration.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicsConfiguration {

    @Value("${kafka.partitions}")
    private int partitions;

    @Bean
    public KafkaAdmin.NewTopics getUsersTopics(@Value("${kafka.users.get.topics.request}") String request,
                                             @Value("${kafka.users.get.topics.response}") String response) {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name(request)
                        .partitions(partitions)
                        .build(),
                TopicBuilder.name(response)
                        .partitions(partitions)
                        .build()
        );
    }
}
