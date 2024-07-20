package org.alsjava.microservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.alsjava.microservice.model.request.CreateDeviceRequest;
import org.alsjava.microservice.model.request.DeleteDeviceRequest;
import org.alsjava.microservice.model.request.UpdateDeviceRequest;
import org.alsjava.microservice.model.response.*;
import org.alsjava.microservice.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping("/get/{id}")
    public ResponseEntity<GetDeviceResponse> get(@Valid @PathVariable @NotNull UUID id) {
        return ResponseEntity.ok(deviceService.get(id));
    }

    @GetMapping("/list")
    public ResponseEntity<ListDeviceResponse> list(@RequestParam(name = "userId") UUID userId,
                                                   @RequestParam(name = "page", defaultValue = "0", required = false) int page,
                                                   @RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok(deviceService.list(userId, page, pageSize));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count(@RequestParam(name = "userId") UUID userId) {
        return ResponseEntity.ok(deviceService.count(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<CreateDeviceResponse> create(@Valid @RequestBody CreateDeviceRequest createDeviceRequest) {
        return ResponseEntity.ok(deviceService.create(createDeviceRequest));
    }

    @PutMapping("/update")
    public ResponseEntity<UpdateDeviceResponse> update(@Valid @RequestBody UpdateDeviceRequest updateDeviceRequest) {
        return ResponseEntity.ok(deviceService.update(updateDeviceRequest));
    }

    @DeleteMapping("/{userId}/{deviceId}")
    public ResponseEntity<DeleteDeviceResponse> delete(@Valid @PathVariable @NotNull UUID userId,
                                                       @Valid @PathVariable @NotNull UUID deviceId) {
        return ResponseEntity.ok(deviceService.delete(DeleteDeviceRequest.builder().userId(userId).deviceId(deviceId).build()));
    }
}
