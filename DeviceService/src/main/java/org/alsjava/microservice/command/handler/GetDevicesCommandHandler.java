package org.alsjava.microservice.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.GetDevicesCommand;
import org.alsjava.microservice.domain.DeviceEntity;
import org.alsjava.microservice.model.response.ListDeviceResponse;
import org.alsjava.microservice.pattern.command.CommandEvent;
import org.alsjava.microservice.pattern.command.CommandHandler;
import org.alsjava.microservice.repository.DeviceRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@CommandEvent(command = GetDevicesCommand.class)
@Slf4j
public class GetDevicesCommandHandler implements CommandHandler<ListDeviceResponse, GetDevicesCommand> {

    private final DeviceRepository deviceRepository;

    @Override
    public ListDeviceResponse handle(GetDevicesCommand command) {
        PageRequest pageRequest = PageRequest.of(command.getPage(), command.getPageSize());
        return ListDeviceResponse.builder().devices(deviceRepository.findAllByUserId(command.getUserId(), pageRequest)
                        .stream()
                        .map(DeviceEntity::toDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
