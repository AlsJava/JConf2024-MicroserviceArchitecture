package org.alsjava.microservice.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.alsjava.microservice.model.request.CreateDeviceRequest;
import org.alsjava.microservice.model.request.UpdateDeviceRequest;
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
@HttpExchange("/api/devices")
public interface DeviceClient {

    @GetExchange("/get/{id}")
    ResponseEntity<GetDeviceResponse> get(@Valid @PathVariable @NotNull UUID id);

    @GetExchange("/list")
    ResponseEntity<ListDeviceResponse> list(@RequestParam(name = "userId") UUID userId,
                                            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize);

    @GetExchange("/count")
    ResponseEntity<Long> count(@RequestParam(name = "userId") UUID userId);

    @PostExchange("/create")
    ResponseEntity<CreateDeviceResponse> create(@Valid @RequestBody CreateDeviceRequest createDeviceRequest);

    @PutExchange("/update")
    ResponseEntity<UpdateDeviceResponse> update(@Valid @RequestBody UpdateDeviceRequest updateDeviceRequest);

    @DeleteExchange("/{userId}/{deviceId}")
    ResponseEntity<DeleteDeviceResponse> delete(@Valid @PathVariable @NotNull UUID userId,
                                                @Valid @PathVariable @NotNull UUID deviceId);
}
