package org.alsjava.microservice.model;

import lombok.*;
import org.alsjava.microservice.enums.DeviceType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeviceDTO {

    private UUID id;
    private String name;
    private String description;
    private DeviceType deviceType;
}
