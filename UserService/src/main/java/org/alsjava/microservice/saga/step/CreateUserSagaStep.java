package org.alsjava.microservice.saga.step;

import lombok.RequiredArgsConstructor;
import org.alsjava.microservice.command.CreateUserCommand;
import org.alsjava.microservice.domain.UserEntity;
import org.alsjava.microservice.model.response.CreateUserResponse;
import org.alsjava.microservice.pattern.saga.model.SagaStep;
import org.alsjava.microservice.pattern.saga.model.SagaStepCompensator;
import org.alsjava.microservice.pattern.saga.model.SagaStepHandler;
import org.alsjava.microservice.repository.UserRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateUserSagaStep implements SagaStep<CreateUserResponse> {

    private final UserRepository userRepository;

    @Override
    public String getName() {
        return "Create User Saga Step";
    }

    @Override
    public SagaStepHandler<CreateUserResponse> getHandler() {
        return sagaPayload -> {
            CreateUserCommand createUserCommand = sagaPayload.getProperty(CreateUserCommand.COMMAND);
            UserEntity user = createUserCommand.toEntity();
            userRepository.save(user);
            sagaPayload.addProperty(CreateUserCommand.USER, user);
        };
    }

    @Override
    public SagaStepCompensator<CreateUserResponse> getCompensator() {
        // Nothing to do here.
        return null;
    }
}
