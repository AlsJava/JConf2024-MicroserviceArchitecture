package org.alsjava.microservice.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.DeleteDeviceCommand;
import org.alsjava.microservice.domain.DeviceEntity;
import org.alsjava.microservice.exception.DeviceNoFoundException;
import org.alsjava.microservice.model.response.DeleteDeviceResponse;
import org.alsjava.microservice.pattern.command.CommandEvent;
import org.alsjava.microservice.pattern.command.CommandHandler;
import org.alsjava.microservice.repository.DeviceRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@CommandEvent(command = DeleteDeviceCommand.class)
@Slf4j
public class DeleteDeviceCommandHandler implements CommandHandler<DeleteDeviceResponse, DeleteDeviceCommand> {

    private final DeviceRepository deviceRepository;

    @Override
    public DeleteDeviceResponse handle(DeleteDeviceCommand command) {
        DeviceEntity device = deviceRepository.findByUserIdAndId(command.getUserId(), command.getDeviceId()).orElseThrow(() -> new DeviceNoFoundException(command.getDeviceId()));
        deviceRepository.delete(device);
        int devicesAvailable = deviceRepository.countAllByUserId(command.getUserId());
        return DeleteDeviceResponse.builder().devicesAvailable(devicesAvailable).build();
    }
}
