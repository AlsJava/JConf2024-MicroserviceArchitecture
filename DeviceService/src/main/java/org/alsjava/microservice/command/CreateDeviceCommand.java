package org.alsjava.microservice.command;

import lombok.*;
import org.alsjava.microservice.domain.DeviceEntity;
import org.alsjava.microservice.enums.DeviceType;
import org.alsjava.microservice.model.response.CreateDeviceResponse;
import org.alsjava.microservice.pattern.command.Command;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateDeviceCommand extends Command<CreateDeviceResponse> {

    private String name;
    private String description;
    private DeviceType deviceType;
    private UUID userId;

    public DeviceEntity toEntity() {
        return DeviceEntity.builder()
                .name(name)
                .description(description)
                .deviceType(deviceType)
                .userId(userId)
                .build();
    }
}
