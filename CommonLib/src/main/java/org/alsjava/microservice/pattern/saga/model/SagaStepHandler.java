package org.alsjava.microservice.pattern.saga.model;

public interface SagaStepHandler<T> {

    void handle(SagaPayload<T> sagaPayload);
}
