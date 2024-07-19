package org.alsjava.microservice.util;

import lombok.extern.slf4j.Slf4j;
import org.alsjava.microservice.exception.CreateDeviceException;
import org.alsjava.microservice.exception.UserNoFoundException;
import org.alsjava.microservice.model.APIError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ExceptionAdvisorHandler {

    @ExceptionHandler(CreateDeviceException.class)
    public ResponseEntity<APIError> handle(CreateDeviceException ex) {
        return createResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNoFoundException.class)
    public ResponseEntity<APIError> handle(UserNoFoundException ex) {
        return createResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<APIError> createResponse(String message, HttpStatus httpStatus) {
        log.info("Generating Error {}  and message {}", httpStatus, message);
        APIError apiError = APIError.builder().timestamp(LocalDateTime.now()).message(message).status(httpStatus.value()).build();
        return ResponseEntity.status(httpStatus).body(apiError);
    }
}
