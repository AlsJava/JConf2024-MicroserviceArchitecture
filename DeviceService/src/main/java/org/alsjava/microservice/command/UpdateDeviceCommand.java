package org.alsjava.microservice.command;

import lombok.*;
import org.alsjava.microservice.enums.DeviceType;
import org.alsjava.microservice.model.response.UpdateDeviceResponse;
import org.alsjava.microservice.pattern.command.Command;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateDeviceCommand extends Command<UpdateDeviceResponse> {

    private UUID id;
    private String name;
    private String description;
    private DeviceType deviceType;
}
