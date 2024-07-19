package org.alsjava.microservice.model.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DeleteUserResponse {

    @Builder.Default
    private boolean result = true;
}
