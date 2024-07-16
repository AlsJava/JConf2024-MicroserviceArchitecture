package org.alsjava.microservice.pattern.command;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.util.Optional;

@ConditionalOnProperty(prefix = "general", name = "cqrs.enable", havingValue = "true")
@RequiredArgsConstructor
@Service
public class CommandBus {

    private final MessagingTemplate messagingTemplate;
    private final MessageChannel commandChannel;

    @SuppressWarnings("unchecked")
    public <R, C> R sendCommand(C command) {
        Message<?> message = messagingTemplate.sendAndReceive(commandChannel, new GenericMessage<>(command));
        return Optional.ofNullable(message).map(msg -> (R) msg.getPayload()).orElse(null);
    }
}
