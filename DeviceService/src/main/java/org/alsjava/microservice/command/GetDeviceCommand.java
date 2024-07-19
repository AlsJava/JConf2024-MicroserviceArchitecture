package org.alsjava.microservice.command;

import lombok.*;
import org.alsjava.microservice.model.response.GetDeviceResponse;
import org.alsjava.microservice.pattern.command.Command;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetDeviceCommand extends Command<GetDeviceResponse> {

    private UUID id;
}
