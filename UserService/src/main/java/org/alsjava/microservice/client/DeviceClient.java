package org.alsjava.microservice.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.exception.CreateDeviceException;
import org.alsjava.microservice.model.request.CreateDeviceRequest;
import org.alsjava.microservice.model.response.CreateDeviceResponse;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeviceClient {

    private final ReplyingKafkaTemplate<String, CreateDeviceRequest, CreateDeviceResponse> createDeviceReplyingTemplate;
    @Value("${pattern.client.timeout}")
    private long clientTimeout;

    @Value("${kafka.devices.create.topics.request}")
    private String createDeviceRequestTopic;

    public CreateDeviceResponse sendCreateDevice(CreateDeviceRequest createDeviceRequest) {
        ProducerRecord<String, CreateDeviceRequest> record = new ProducerRecord<>(createDeviceRequestTopic, createDeviceRequest);
        RequestReplyFuture<String, CreateDeviceRequest, CreateDeviceResponse> replyFuture = createDeviceReplyingTemplate.sendAndReceive(record);
        try {
            SendResult<String, CreateDeviceRequest> sendResult = replyFuture.getSendFuture().get(clientTimeout, TimeUnit.SECONDS);
            sendResult.getProducerRecord().headers().forEach(header -> log.debug("Header -- {} :: {}", header.key(), header.value()));
            ConsumerRecord<String, CreateDeviceResponse> consumerRecord = replyFuture.get();
            CreateDeviceResponse value = consumerRecord.value();
            log.debug("Value :: {}", value);
            return value;
        } catch (Exception ex) {
            log.error("Cant create Device", ex);
            throw new CreateDeviceException(createDeviceRequest);
        }
    }

}
