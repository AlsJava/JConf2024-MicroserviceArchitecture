package org.alsjava.microservice.command.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.command.GetUsersCommand;
import org.alsjava.microservice.domain.UserEntity;
import org.alsjava.microservice.model.response.ListUserResponse;
import org.alsjava.microservice.pattern.command.CommandEvent;
import org.alsjava.microservice.pattern.command.CommandHandler;
import org.alsjava.microservice.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@CommandEvent(command = GetUsersCommand.class)
@Slf4j
public class GetUsersCommandHandler implements CommandHandler<ListUserResponse, GetUsersCommand> {

    private final UserRepository userRepository;

    @Override
    public ListUserResponse handle(GetUsersCommand command) {
        PageRequest pageRequest = PageRequest.of(command.getPage(), command.getSize());
        return ListUserResponse.builder().users(userRepository.findAll(pageRequest)
                .stream()
                .map(UserEntity::toDTO)
                .collect(Collectors.toList())).build();
    }
}
