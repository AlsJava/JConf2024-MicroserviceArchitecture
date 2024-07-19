package org.alsjava.microservice.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.alsjava.microservice.enums.UserType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateUserRequest {

    @Schema(hidden = true)
    private UUID transactionId;

    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private UserType userType;
}
