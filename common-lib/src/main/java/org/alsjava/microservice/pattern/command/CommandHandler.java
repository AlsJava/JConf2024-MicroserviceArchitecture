package org.alsjava.microservice.pattern.command;

import java.util.concurrent.ExecutionException;

public interface CommandHandler<R, C extends Command<R>> {

    R handle(C command) throws ExecutionException;
}
