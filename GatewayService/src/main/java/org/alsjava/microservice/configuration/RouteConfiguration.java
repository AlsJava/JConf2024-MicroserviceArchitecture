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
                .route("route-inventory-service", route -> route.path("/api/inventory/**")
                        .filters(filter -> filter.stripPrefix(2).circuitBreaker(config -> config
                                .setName("Fallback")
                                .setFallbackUri("forward:/fallback")))
                        .uri("lb://INVENTORY-SERVICE"))
                .route("route-invoice-service", route -> route.path("/api/invoice/**")
                        .filters(filter -> filter.stripPrefix(2).circuitBreaker(config -> config
                                .setName("Fallback")
                                .setFallbackUri("forward:/fallback")))
                        .uri("lb://INVOICE-SERVICE"))
                .build();
    }

}
