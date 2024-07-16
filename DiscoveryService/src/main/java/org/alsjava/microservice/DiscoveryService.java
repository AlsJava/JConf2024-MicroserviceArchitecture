package org.alsjava.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EnableCaching
public class DiscoveryService {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryService.class, args);
    }
}
