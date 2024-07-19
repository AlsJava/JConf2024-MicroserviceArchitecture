package org.alsjava.microservice.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteDeviceRequest {

    @Schema(hidden = true)
    private UUID transactionId;

    private UUID userId;
    private UUID deviceId;
}
