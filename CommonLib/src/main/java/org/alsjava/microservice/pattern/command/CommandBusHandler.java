package org.alsjava.microservice.pattern.command;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@ConditionalOnProperty(prefix = "pattern", name = "cqrs.enabled", havingValue = "true")
@Component
@RequiredArgsConstructor
public class CommandBusHandler {

    private final CommandProvider commandProvider;

    @SuppressWarnings("unchecked")
    @ServiceActivator(inputChannel = "commandChannel")
    public <R> R executeCommand(Command<R> command) throws ExecutionException {
        return (R) commandProvider.get(command.getClass()).handle(command);
    }
}
