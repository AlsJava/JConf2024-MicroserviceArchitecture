package org.alsjava.microservice.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.UpdateUserCommand;
import org.alsjava.microservice.domain.UserEntity;
import org.alsjava.microservice.exception.UserNoFoundException;
import org.alsjava.microservice.model.response.UpdateUserResponse;
import org.alsjava.microservice.pattern.command.CommandEvent;
import org.alsjava.microservice.pattern.command.CommandHandler;
import org.alsjava.microservice.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@CommandEvent(command = UpdateUserCommand.class)
@Slf4j
public class UpdateUserCommandHandler implements CommandHandler<UpdateUserResponse, UpdateUserCommand> {

    private final UserRepository userRepository;

    @Override
    public UpdateUserResponse handle(UpdateUserCommand command) {
        UserEntity userEntity = userRepository.findById(command.getId()).orElseThrow(() -> new UserNoFoundException(command.getId()));
        userEntity.setName(command.getName());
        userEntity.setDescription(command.getDescription());
        userEntity.setUserType(command.getUserType());
        userRepository.save(userEntity);
        return UpdateUserResponse.builder().user(userEntity.toDTO()).build();
    }
}
