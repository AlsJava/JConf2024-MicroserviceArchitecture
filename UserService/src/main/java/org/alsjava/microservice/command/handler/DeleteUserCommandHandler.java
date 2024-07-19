package org.alsjava.microservice.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.GetUserCommand;
import org.alsjava.microservice.domain.UserEntity;
import org.alsjava.microservice.exception.UserNoFoundException;
import org.alsjava.microservice.model.response.GetUserResponse;
import org.alsjava.microservice.pattern.command.CommandEvent;
import org.alsjava.microservice.pattern.command.CommandHandler;
import org.alsjava.microservice.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@CommandEvent(command = GetUserCommand.class)
@Slf4j
public class DeleteUserCommandHandler implements CommandHandler<GetUserResponse, GetUserCommand> {

    private final UserRepository userRepository;

    @Override
    public GetUserResponse handle(GetUserCommand command) {
        return GetUserResponse.builder()
                .user(userRepository.findById(command.getId()).map(UserEntity::toDTO).orElseThrow(() -> new UserNoFoundException(command.getId())))
                .build();
    }
}
