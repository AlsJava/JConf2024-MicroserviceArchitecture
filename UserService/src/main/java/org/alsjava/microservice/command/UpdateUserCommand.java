package org.alsjava.microservice.command;

import lombok.*;
import org.alsjava.microservice.enums.UserType;
import org.alsjava.microservice.model.response.UpdateUserResponse;
import org.alsjava.microservice.pattern.command.Command;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateUserCommand extends Command<UpdateUserResponse> {

    private UUID id;
    private String name;
    private String description;
    private UserType userType;
}
