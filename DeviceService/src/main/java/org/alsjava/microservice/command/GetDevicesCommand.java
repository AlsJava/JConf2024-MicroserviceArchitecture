package org.alsjava.microservice.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.alsjava.microservice.model.response.ListDeviceResponse;
import org.alsjava.microservice.pattern.command.Command;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetDevicesCommand extends Command<ListDeviceResponse> {

    private UUID userId;
    private int page;
    private int pageSize;

}
