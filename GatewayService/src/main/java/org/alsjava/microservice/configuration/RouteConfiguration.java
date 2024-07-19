package org.alsjava.microservice.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {

    @Bean
    public RouteLocator customRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("route-device-service", route -> route.path("/api/devices/**")
                        .filters(filter -> filter.stripPrefix(2).circuitBreaker(config -> config
                                .setName("Fallback")
                                .setFallbackUri("forward:/fallback")))
                        .uri("lb://DEVICE-SERVICE"))
                .route("route-user-service", route -> route.path("/api/users/**")
                        .filters(filter -> filter.stripPrefix(2).circuitBreaker(config -> config
                                .setName("Fallback")
                                .setFallbackUri("forward:/fallback")))
                        .uri("lb://USER-SERVICE"))
                .build();
    }

}
