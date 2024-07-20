package org.alsjava.microservice.command;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.alsjava.microservice.pattern.command.Command;

@Builder
@Getter
@Setter
public class CountUsersCommand extends Command<Long> {

}
