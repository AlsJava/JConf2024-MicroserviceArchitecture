package org.alsjava.microservice.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteDeviceResponse {

    private int devicesAvailable;

    public boolean hasDevices() {
        return devicesAvailable > 0;
    }

}
