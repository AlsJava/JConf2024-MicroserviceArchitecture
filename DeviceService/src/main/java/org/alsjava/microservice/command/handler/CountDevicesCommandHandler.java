package org.alsjava.microservice.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.CountDevicesCommand;
import org.alsjava.microservice.pattern.command.CommandEvent;
import org.alsjava.microservice.pattern.command.CommandHandler;
import org.alsjava.microservice.repository.DeviceRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@CommandEvent(command = CountDevicesCommand.class)
@Slf4j
public class CountDevicesCommandHandler implements CommandHandler<Long, CountDevicesCommand> {

    private final DeviceRepository deviceRepository;

    @Override
    public Long handle(CountDevicesCommand command) {
        return (long) deviceRepository.countAllByUserId(command.getUserId());
    }
}
