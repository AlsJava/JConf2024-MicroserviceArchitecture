package org.alsjava.microservice.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.alsjava.microservice.model.request.CreateDeviceRequest;
import org.alsjava.microservice.model.request.UpdateDeviceRequest;
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
public interface DeviceClient {

    @GetMapping("/get/{id}")
    ResponseEntity<GetDeviceResponse> get(@Valid @PathVariable @NotNull UUID id);

    @GetMapping("/list")
    ResponseEntity<ListDeviceResponse> list(@RequestParam(name = "userId") UUID userId,
                                            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                            @RequestParam(name = "size", defaultValue = "10", required = false) int size);

    @PostMapping("/create")
    ResponseEntity<CreateDeviceResponse> create(@Valid @RequestBody CreateDeviceRequest createDeviceRequest);

    @PutMapping("/update")
    ResponseEntity<UpdateDeviceResponse> update(@Valid @RequestBody UpdateDeviceRequest updateDeviceRequest);

    @DeleteMapping("/{userId}/{deviceId}")
    ResponseEntity<DeleteDeviceResponse> delete(@Valid @PathVariable @NotNull UUID userId,
                                                @Valid @PathVariable @NotNull UUID deviceId);
}
