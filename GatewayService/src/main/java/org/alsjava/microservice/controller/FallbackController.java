package org.alsjava.microservice.controller;

import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/fallbak")
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public Mono<String> fallback(ServerWebExchange serverWebExchange) {
        Exception exception = serverWebExchange.getAttribute(ServerWebExchangeUtils.CIRCUITBREAKER_EXECUTION_EXCEPTION_ATTR);
        return Mono.just("Internal Request Timeout on Gateway" + (exception == null ? "" : ": " + exception.getMessage()));
    }

}
