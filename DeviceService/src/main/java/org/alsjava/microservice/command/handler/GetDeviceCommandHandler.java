package org.alsjava.microservice.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.GetDeviceCommand;
import org.alsjava.microservice.domain.DeviceEntity;
import org.alsjava.microservice.exception.DeviceNoFoundException;
import org.alsjava.microservice.model.response.GetDeviceResponse;
import org.alsjava.microservice.pattern.command.CommandEvent;
import org.alsjava.microservice.pattern.command.CommandHandler;
import org.alsjava.microservice.repository.DeviceRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@CommandEvent(command = GetDeviceCommand.class)
@Slf4j
public class GetDeviceCommandHandler implements CommandHandler<GetDeviceResponse, GetDeviceCommand> {

    private final DeviceRepository deviceRepository;

    @Override
    public GetDeviceResponse handle(GetDeviceCommand command) {
        return GetDeviceResponse.builder()
                .device(deviceRepository.findById(command.getId()).map(DeviceEntity::toDTO).orElseThrow(() -> new DeviceNoFoundException(command.getId())))
                .build();
    }
}
