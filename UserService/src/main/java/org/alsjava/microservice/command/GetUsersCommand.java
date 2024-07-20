package org.alsjava.microservice.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.alsjava.microservice.model.response.ListUserResponse;
import org.alsjava.microservice.pattern.command.Command;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class GetUsersCommand extends Command<ListUserResponse> {

    private int page;
    private int pageSize;

}
