package org.alsjava.microservice.model;

import lombok.*;
import org.alsjava.microservice.enums.UserType;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {

    private UUID id;
    private String name;
    private String description;
    private UserType userType;
}
