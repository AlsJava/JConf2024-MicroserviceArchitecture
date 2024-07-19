package org.alsjava.microservice.exception;

import java.text.MessageFormat;
import java.util.UUID;

public class UserNoFoundException extends RuntimeException {

    public UserNoFoundException(UUID userId) {
        super(MessageFormat.format("User {0} no found", userId.toString()));
    }
}
