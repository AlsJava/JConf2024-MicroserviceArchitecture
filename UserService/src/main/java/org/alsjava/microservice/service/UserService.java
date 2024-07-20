package org.alsjava.microservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.*;
import org.alsjava.microservice.model.request.CreateUserRequest;
import org.alsjava.microservice.model.request.GetUserRequest;
import org.alsjava.microservice.model.request.UpdateUserRequest;
import org.alsjava.microservice.model.response.*;
import org.alsjava.microservice.pattern.command.CommandBus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

    private final CommandBus commandBus;

    public GetUserResponse get(GetUserRequest getUserRequest) {
        log.info("Getting User : {}", getUserRequest.getId());
        return commandBus.sendCommand(GetUserCommand.builder()
                .id(getUserRequest.getId())
                .build());
    }

    public ListUserResponse list(int page, int pageSize) {
        return commandBus.sendCommand(GetUsersCommand.builder()
                .page(page)
                .pageSize(pageSize)
                .build());
    }

    public Long count() {
        return commandBus.sendCommand(CountUsersCommand.builder().build());
    }

    public CreateUserResponse create(CreateUserRequest createUserRequest) {
        return commandBus.sendCommand(CreateUserCommand.builder()
                .name(createUserRequest.getName())
                .description(createUserRequest.getDescription())
                .userType(createUserRequest.getUserType())
                .build());
    }

    public UpdateUserResponse update(UpdateUserRequest updateUserRequest) {
        return commandBus.sendCommand(UpdateUserCommand.builder()
                .id(updateUserRequest.getId())
                .name(updateUserRequest.getName())
                .description(updateUserRequest.getDescription())
                .userType(updateUserRequest.getUserType())
                .build());
    }

    public DeleteUserResponse delete(UUID id) {
        return commandBus.sendCommand(DeleteUserCommand.builder()
                .id(id)
                .build());
    }
}
