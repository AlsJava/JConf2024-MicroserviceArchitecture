package org.alsjava.microservice.saga;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.alsjava.microservice.command.CreateUserCommand;
import org.alsjava.microservice.model.response.CreateUserResponse;
import org.alsjava.microservice.pattern.saga.model.Saga;
import org.alsjava.microservice.pattern.saga.model.SagaPayload;
import org.alsjava.microservice.saga.step.CreateDeviceSagaStep;
import org.alsjava.microservice.saga.step.CreateUserSagaStep;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SagaFactory {

    public static Saga<CreateUserResponse> createUserSaga(UUID key, CreateUserCommand depositCommand) {
        SagaPayload<CreateUserResponse> sagaPayload = new SagaPayload<>();
        sagaPayload.addProperty(CreateUserCommand.KEY, key);
        sagaPayload.addProperty(CreateUserCommand.COMMAND, depositCommand);
        return Saga.<CreateUserResponse>builder()
                .name("Create User with devices SAGA")
                .key(key)
                .payload(sagaPayload)
                .requiredStep(List.of(
                        CreateUserSagaStep.class,
                        CreateDeviceSagaStep.class
                ))
                .build();
    }
}
