package org.alsjava.microservice.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.CreateDeviceCommand;
import org.alsjava.microservice.domain.DeviceEntity;
import org.alsjava.microservice.model.response.CreateDeviceResponse;
import org.alsjava.microservice.pattern.command.CommandEvent;
import org.alsjava.microservice.pattern.command.CommandHandler;
import org.alsjava.microservice.repository.DeviceRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@CommandEvent(command = CreateDeviceCommand.class)
@Slf4j
public class CreateDeviceCommandHandler implements CommandHandler<CreateDeviceResponse, CreateDeviceCommand> {

    private final DeviceRepository deviceRepository;

    @Override
    public CreateDeviceResponse handle(CreateDeviceCommand command) {
        DeviceEntity device = command.toEntity();
        deviceRepository.save(device);
        return CreateDeviceResponse.builder().device(device.toDTO()).build();
    }
}
