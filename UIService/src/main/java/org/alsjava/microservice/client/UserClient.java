package org.alsjava.microservice.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.alsjava.microservice.model.request.CreateUserRequest;
import org.alsjava.microservice.model.request.UpdateUserRequest;
import org.alsjava.microservice.model.response.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * We don't use Feign here XD
 * We use Pure Spring solution without add to many dependencies
 *
 * @see <a href="https://www.baeldung.com/spring-6-http-interface">Declarative Interface</a>
 */
public interface UserClient {

    @GetMapping("/get/{id}")
    ResponseEntity<GetUserResponse> get(@Valid @PathVariable @NotNull UUID id);

    @GetMapping("/list")
    ResponseEntity<ListUserResponse> list(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                          @RequestParam(name = "size", defaultValue = "10", required = false) int size);

    @PostMapping("/create")
    ResponseEntity<CreateUserResponse> create(@Valid @RequestBody CreateUserRequest createUserRequest);

    @PutMapping("/update")
    ResponseEntity<UpdateUserResponse> update(@Valid @RequestBody UpdateUserRequest updateUserRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<DeleteUserResponse> delete(@Valid @PathVariable @NotNull UUID id);
}
