package org.alsjava.microservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.model.request.GetUserRequest;
import org.alsjava.microservice.model.response.GetUserResponse;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserKafkaService {

    private final KafkaTemplate<String, GetUserResponse> getUserResponseKafkaTemplate;
    private final UserService userService;

    @Value("${kafka.users.get.topics.response}")
    private String getUserResponseTopic;

    @KafkaListener(
            topics = "${kafka.users.get.topics.request}",
            groupId = "${kafka.users.get.group}",
            containerFactory = "getUserKafkaListenerContainerFactory")
    public void listenGetUserRequest(ConsumerRecord<String, GetUserRequest> consumerRecord, @Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) throws ExecutionException, InterruptedException {
        GetUserRequest value = consumerRecord.value();
        GetUserResponse getUserResponse = userService.get(value);
        ProducerRecord<String, GetUserResponse> producerRecord = new ProducerRecord<>(getUserResponseTopic, getUserResponse);
        producerRecord.headers().add(KafkaHeaders.CORRELATION_ID, correlation); // perform Reply pattern
        getUserResponseKafkaTemplate.send(producerRecord).get();
    }
}
