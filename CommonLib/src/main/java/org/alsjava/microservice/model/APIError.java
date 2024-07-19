package org.alsjava.microservice.model;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class APIError {

    private LocalDateTime timestamp;
    private Integer status;
    private String message;
}
