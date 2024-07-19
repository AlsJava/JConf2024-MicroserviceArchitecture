package org.alsjava.microservice.pattern.saga.model;

public interface SagaStepCompensator<T> {

    void handle(SagaPayload<T> sagaPayload);
}
