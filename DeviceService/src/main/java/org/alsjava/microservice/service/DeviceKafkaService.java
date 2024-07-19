package org.alsjava.microservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.model.request.CreateDeviceRequest;
import org.alsjava.microservice.model.request.DeleteDeviceRequest;
import org.alsjava.microservice.model.response.CreateDeviceResponse;
import org.alsjava.microservice.model.response.DeleteDeviceResponse;
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
public class DeviceKafkaService {

    private final KafkaTemplate<String, CreateDeviceResponse> createDeviceResponseKafkaTemplate;
    private final KafkaTemplate<String, DeleteDeviceResponse> deleteDeviceResponseKafkaTemplate;
    private final DeviceService deviceService;

    @Value("${kafka.devices.create.topics.response}")
    private String createDeviceResponseTopic;
    @Value("${kafka.devices.delete.topics.response}")
    private String deleteDeviceResponseTopic;

    @KafkaListener(
            topics = "${kafka.devices.create.topics.request}",
            groupId = "${kafka.devices.create.group}",
            containerFactory = "createDeviceKafkaListenerContainerFactory")
    public void listenCreateDeviceRequest(ConsumerRecord<String, CreateDeviceRequest> createDeviceRequest, @Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) throws ExecutionException, InterruptedException {
        CreateDeviceRequest value = createDeviceRequest.value();
        CreateDeviceResponse createDeviceResponse = deviceService.create(value);
        ProducerRecord<String, CreateDeviceResponse> record = new ProducerRecord<>(createDeviceResponseTopic, createDeviceResponse);
        record.headers().add(KafkaHeaders.CORRELATION_ID, correlation); // perform Reply pattern
        createDeviceResponseKafkaTemplate.send(record).get();
    }

    @KafkaListener(
            topics = "${kafka.devices.delete.topics.request}",
            groupId = "${kafka.devices.delete.group}",
            containerFactory = "deleteDeviceKafkaListenerContainerFactory")
    public void listenDeleteDeviceRequest(ConsumerRecord<String, DeleteDeviceRequest> deleteDeviceRequest, @Header(KafkaHeaders.CORRELATION_ID) byte[] correlation) throws ExecutionException, InterruptedException {
        DeleteDeviceResponse deleteDeviceResponse = deviceService.delete(deleteDeviceRequest.value());
        ProducerRecord<String, DeleteDeviceResponse> record = new ProducerRecord<>(deleteDeviceResponseTopic, deleteDeviceResponse);
        record.headers().add(KafkaHeaders.CORRELATION_ID, correlation); // perform Reply pattern
        deleteDeviceResponseKafkaTemplate.send(record).get();
    }

}
