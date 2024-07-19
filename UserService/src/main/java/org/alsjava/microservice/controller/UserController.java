package org.alsjava.microservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.alsjava.microservice.model.request.CreateUserRequest;
import org.alsjava.microservice.model.request.GetUserRequest;
import org.alsjava.microservice.model.request.UpdateUserRequest;
import org.alsjava.microservice.model.response.*;
import org.alsjava.microservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/get/{id}")
    public ResponseEntity<GetUserResponse> get(@Valid @PathVariable @NotNull UUID id) {
        return ResponseEntity.ok(userService.get(GetUserRequest.builder().id(id).build()));
    }

    @GetMapping("/list")
    public ResponseEntity<ListUserResponse> list(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                 @RequestParam(name = "size", defaultValue = "10", required = false) int size) {
        return ResponseEntity.ok(userService.list(page, size));
    }

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> create(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.create(createUserRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateUserResponse> update(@Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.update(updateUserRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteUserResponse> delete(@Valid @PathVariable @NotNull UUID id) {
        return ResponseEntity.ok(userService.delete(id));
    }

}
