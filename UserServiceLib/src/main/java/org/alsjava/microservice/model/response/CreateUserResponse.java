package org.alsjava.microservice.model.response;

import lombok.*;
import org.alsjava.microservice.model.UserDTO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CreateUserResponse {

    private UserDTO user;
}
