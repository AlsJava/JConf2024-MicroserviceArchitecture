package org.alsjava.microservice.model.response;

import lombok.*;
import org.alsjava.microservice.model.UserDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ListUserResponse {

    private List<UserDTO> users;
}
