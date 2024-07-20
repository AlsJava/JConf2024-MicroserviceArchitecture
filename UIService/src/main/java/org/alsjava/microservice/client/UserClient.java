package org.alsjava.microservice.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.alsjava.microservice.model.request.CreateUserRequest;
import org.alsjava.microservice.model.request.UpdateUserRequest;
import org.alsjava.microservice.model.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.*;

import java.util.UUID;

/**
 * We don't use Feign here XD
 * We use Pure Spring solution without add to many dependencies
 *
 * @see <a href="https://www.baeldung.com/spring-6-http-interface">Declarative Interface</a>
 */
@HttpExchange("/api/users")
public interface UserClient {

    @GetExchange("/get/{id}")
    ResponseEntity<GetUserResponse> get(@Valid @PathVariable @NotNull UUID id);

    @GetExchange("/list")
    ResponseEntity<ListUserResponse> list(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                          @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize);

    @GetExchange("/count")
    ResponseEntity<Long> count();

    @PostExchange("/create")
    ResponseEntity<CreateUserResponse> create(@Valid @RequestBody CreateUserRequest createUserRequest);

    @PutExchange("/update")
    ResponseEntity<UpdateUserResponse> update(@Valid @RequestBody UpdateUserRequest updateUserRequest);

    @DeleteExchange("/{id}")
    ResponseEntity<DeleteUserResponse> delete(@Valid @PathVariable @NotNull UUID id);
}
