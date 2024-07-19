package org.alsjava.microservice.command;

import lombok.*;
import org.alsjava.microservice.model.response.DeleteDeviceResponse;
import org.alsjava.microservice.pattern.command.Command;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteDeviceCommand extends Command<DeleteDeviceResponse> {

    private UUID userId;
    private UUID deviceId;

}
