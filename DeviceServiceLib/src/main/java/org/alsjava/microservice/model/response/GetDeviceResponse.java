package org.alsjava.microservice.model.response;

import lombok.*;
import org.alsjava.microservice.model.DeviceDTO;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class GetDeviceResponse {

    private DeviceDTO device;
}
