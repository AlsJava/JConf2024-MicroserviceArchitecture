package org.alsjava.microservice.model.response;

import lombok.*;
import org.alsjava.microservice.model.DeviceDTO;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ListDeviceResponse {

    private List<DeviceDTO> devices;
}
