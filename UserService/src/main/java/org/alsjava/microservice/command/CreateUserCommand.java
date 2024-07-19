package org.alsjava.microservice.command;

import lombok.*;
import org.alsjava.microservice.domain.UserEntity;
import org.alsjava.microservice.enums.UserType;
import org.alsjava.microservice.model.response.CreateUserResponse;
import org.alsjava.microservice.pattern.command.Command;
import org.alsjava.microservice.pattern.saga.model.SagaPayLoadKey;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateUserCommand extends Command<CreateUserResponse> {

    public static final SagaPayLoadKey<UUID> KEY = new SagaPayLoadKey<>("key", UUID.class);
    public static final SagaPayLoadKey<CreateUserCommand> COMMAND = new SagaPayLoadKey<>("command", CreateUserCommand.class);
    public static final SagaPayLoadKey<UserEntity> USER = new SagaPayLoadKey<>("user", UserEntity.class);

    private String name;
    private String description;
    private UserType userType;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .name(name)
                .description(description)
                .userType(userType)
                .build();
    }
}
