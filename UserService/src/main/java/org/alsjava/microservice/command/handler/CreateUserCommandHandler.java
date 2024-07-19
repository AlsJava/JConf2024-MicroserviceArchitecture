package org.alsjava.microservice.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.CreateUserCommand;
import org.alsjava.microservice.model.response.CreateUserResponse;
import org.alsjava.microservice.pattern.command.CommandEvent;
import org.alsjava.microservice.pattern.command.CommandHandler;
import org.alsjava.microservice.pattern.saga.SagaOrchestrator;
import org.alsjava.microservice.pattern.saga.model.Saga;
import org.alsjava.microservice.saga.SagaFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@CommandEvent(command = CreateUserCommand.class)
@Slf4j
public class CreateUserCommandHandler implements CommandHandler<CreateUserResponse, CreateUserCommand> {

    private final SagaOrchestrator sagaOrchestrator;

    @Value("${pattern.saga.timeout}")
    private int timeout;

    @Override
    public CreateUserResponse handle(CreateUserCommand command) {
        Saga<CreateUserResponse> saga = SagaFactory.createUserSaga(UUID.randomUUID(), command);
        return sagaOrchestrator.orchestrate(saga, timeout);
    }
}
