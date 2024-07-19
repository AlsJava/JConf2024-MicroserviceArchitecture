package org.alsjava.microservice.exception;

import org.alsjava.microservice.model.request.DeleteDeviceRequest;

import java.text.MessageFormat;

public class DeleteDeviceException extends RuntimeException {

    public DeleteDeviceException(DeleteDeviceRequest deleteDeviceRequest) {
        super(MessageFormat.format("Cant delete Device :: {0}", deleteDeviceRequest.toString()));
    }
}
