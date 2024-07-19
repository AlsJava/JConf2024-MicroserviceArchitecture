package org.alsjava.microservice.pattern.saga.model;

public class SagaException extends RuntimeException {

    public SagaException(Throwable cause) {
        super(cause);
    }
}
