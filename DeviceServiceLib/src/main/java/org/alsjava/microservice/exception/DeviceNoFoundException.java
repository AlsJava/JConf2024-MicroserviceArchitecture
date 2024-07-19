package org.alsjava.microservice.exception;

import java.text.MessageFormat;
import java.util.UUID;

public class DeviceNoFoundException extends RuntimeException {

    public DeviceNoFoundException(UUID deviceId) {
        super(MessageFormat.format("Device {0} no found", deviceId.toString()));
    }
}
