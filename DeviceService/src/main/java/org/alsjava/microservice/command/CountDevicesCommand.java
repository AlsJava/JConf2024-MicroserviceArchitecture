package org.alsjava.microservice.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.alsjava.microservice.pattern.command.Command;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class CountDevicesCommand extends Command<Long> {

    private UUID userId;

}
