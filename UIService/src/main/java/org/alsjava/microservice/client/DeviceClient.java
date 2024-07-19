package org.alsjava.microservice.client;

import org.alsjava.microservice.model.DeviceDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DeviceClient {

    public List<DeviceDTO> list(UUID userId, int page, int pageSize) {
        return null;
    }
}
