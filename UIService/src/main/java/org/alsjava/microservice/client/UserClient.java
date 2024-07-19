package org.alsjava.microservice.client;

import org.alsjava.microservice.model.UserDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserClient {

    public List<UserDTO> list(int page, int pageSize) {
        return null;
    }
}
