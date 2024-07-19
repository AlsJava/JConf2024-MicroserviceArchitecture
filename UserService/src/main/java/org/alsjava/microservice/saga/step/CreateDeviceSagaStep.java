package org.alsjava.microservice.saga.step;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.client.DeviceClient;
import org.alsjava.microservice.command.CreateUserCommand;
import org.alsjava.microservice.domain.UserEntity;
import org.alsjava.microservice.enums.DeviceType;
import org.alsjava.microservice.model.request.CreateDeviceRequest;
import org.alsjava.microservice.model.response.CreateUserResponse;
import org.alsjava.microservice.pattern.saga.model.SagaStep;
import org.alsjava.microservice.pattern.saga.model.SagaStepCompensator;
import org.alsjava.microservice.pattern.saga.model.SagaStepHandler;
import org.alsjava.microservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
@Slf4j
public class CreateDeviceSagaStep implements SagaStep<CreateUserResponse> {

    private final DeviceClient deviceClient;
    private final UserRepository userRepository;

    @Override
    public String getName() {
        return "Create Device Saga Step";
    }

    @Override
    public SagaStepHandler<CreateUserResponse> getHandler() {
        return sagaPayload -> {
            UUID key = sagaPayload.getProperty(CreateUserCommand.KEY);
            UserEntity userEntity = sagaPayload.getProperty(CreateUserCommand.USER);
            CreateDeviceRequest createDeviceRequest = CreateDeviceRequest.builder()
                    .transactionId(key)
                    .name("Default")
                    .description("Initial Device")
                    .deviceType(DeviceType.LAPTOP)
                    .userId(userEntity.getId())
                    .build();
            deviceClient.sendCreateDevice(createDeviceRequest);
            sagaPayload.setResult(CreateUserResponse.builder().user(userEntity.toDTO()).build());
        };
    }

    @Override
    public SagaStepCompensator<CreateUserResponse> getCompensator() {
        return sagaPayload -> {
            // Already create a User, we need delete it
            UserEntity userEntity = sagaPayload.getProperty(CreateUserCommand.USER);
            if (userEntity != null) {
                log.info("Removing User {} :: cant create Device", userEntity.getId());
                userRepository.delete(userEntity);
            }
        };
    }
}
