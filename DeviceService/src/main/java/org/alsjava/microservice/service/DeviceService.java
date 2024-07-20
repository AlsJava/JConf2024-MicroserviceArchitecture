package org.alsjava.microservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.*;
import org.alsjava.microservice.model.request.CreateDeviceRequest;
import org.alsjava.microservice.model.request.DeleteDeviceRequest;
import org.alsjava.microservice.model.request.UpdateDeviceRequest;
import org.alsjava.microservice.model.response.*;
import org.alsjava.microservice.pattern.command.CommandBus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service("deviceServiceMS") // Kafka had a bean with DeviceService name
public class DeviceService {

    private final CommandBus commandBus;

    public GetDeviceResponse get(UUID id) {
        return commandBus.sendCommand(GetDeviceCommand.builder().id(id).build());
    }

    public ListDeviceResponse list(UUID userId, int page, int pageSize) {
        return commandBus.sendCommand(GetDevicesCommand.builder().userId(userId).page(page).pageSize(pageSize).build());
    }

    public Long count(UUID userId) {
        return commandBus.sendCommand(CountDevicesCommand.builder().userId(userId).build());
    }

    public CreateDeviceResponse create(CreateDeviceRequest createDeviceRequest) {
        log.info("Creating device : {}", createDeviceRequest);
        return commandBus.sendCommand(CreateDeviceCommand.builder().name(createDeviceRequest.getName()).description(createDeviceRequest.getDescription()).deviceType(createDeviceRequest.getDeviceType()).userId(createDeviceRequest.getUserId()).build());
    }

    public UpdateDeviceResponse update(UpdateDeviceRequest updateDeviceRequest) {
        return commandBus.sendCommand(UpdateDeviceCommand.builder().id(updateDeviceRequest.getId()).name(updateDeviceRequest.getName()).description(updateDeviceRequest.getDescription()).deviceType(updateDeviceRequest.getDeviceType()).build());
    }

    public DeleteDeviceResponse delete(DeleteDeviceRequest deleteDeviceRequest) {
        log.info("Deleting device : {}", deleteDeviceRequest);
        return commandBus.sendCommand(DeleteDeviceCommand.builder().userId(deleteDeviceRequest.getUserId()).deviceId(deleteDeviceRequest.getDeviceId()).build());
    }

}
