package org.alsjava.microservice.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.alsjava.microservice.enums.DeviceType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UpdateDeviceRequest {

    @Schema(hidden = true)
    private UUID transactionId;

    @NotNull
    private UUID id;
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private DeviceType deviceType;
}
