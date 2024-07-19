package org.alsjava.microservice.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.UpdateDeviceCommand;
import org.alsjava.microservice.domain.DeviceEntity;
import org.alsjava.microservice.exception.DeviceNoFoundException;
import org.alsjava.microservice.model.response.UpdateDeviceResponse;
import org.alsjava.microservice.pattern.command.CommandEvent;
import org.alsjava.microservice.pattern.command.CommandHandler;
import org.alsjava.microservice.repository.DeviceRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@CommandEvent(command = UpdateDeviceCommand.class)
@Slf4j
public class UpdateDeviceCommandHandler implements CommandHandler<UpdateDeviceResponse, UpdateDeviceCommand> {

    private final DeviceRepository deviceRepository;

    @Override
    public UpdateDeviceResponse handle(UpdateDeviceCommand command) {
        DeviceEntity deviceEntity = deviceRepository.findById(command.getId()).orElseThrow(() -> new DeviceNoFoundException(command.getId()));
        deviceEntity.setName(command.getName());
        deviceEntity.setDescription(command.getDescription());
        deviceEntity.setDeviceType(command.getDeviceType());
        deviceRepository.save(deviceEntity);
        return UpdateDeviceResponse.builder().device(deviceEntity.toDTO()).build();
    }
}
