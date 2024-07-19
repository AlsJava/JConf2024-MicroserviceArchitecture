package org.alsjava.microservice.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetUserRequest {

    @Schema(hidden = true)
    private UUID transactionId;

    @NotNull
    private UUID id;
}
