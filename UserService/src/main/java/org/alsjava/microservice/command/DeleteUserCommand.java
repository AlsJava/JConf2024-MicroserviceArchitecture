package org.alsjava.microservice.command;

import lombok.*;
import org.alsjava.microservice.model.response.DeleteUserResponse;
import org.alsjava.microservice.pattern.command.Command;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteUserCommand extends Command<DeleteUserResponse> {

    private UUID id;
}
