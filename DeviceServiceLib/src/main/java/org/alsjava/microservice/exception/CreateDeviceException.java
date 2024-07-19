package org.alsjava.microservice.exception;

import org.alsjava.microservice.model.request.CreateDeviceRequest;

import java.text.MessageFormat;

public class CreateDeviceException extends RuntimeException {

    public CreateDeviceException(CreateDeviceRequest createDeviceRequest) {
        super(MessageFormat.format("Cant create Device :: {0}", createDeviceRequest.toString()));
    }
}
