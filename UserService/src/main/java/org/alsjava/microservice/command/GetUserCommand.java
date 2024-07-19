package org.alsjava.microservice.command;

import lombok.*;
import org.alsjava.microservice.model.response.GetUserResponse;
import org.alsjava.microservice.pattern.command.Command;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetUserCommand extends Command<GetUserResponse> {

    private UUID id;
}
