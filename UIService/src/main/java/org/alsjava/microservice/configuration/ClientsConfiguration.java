package org.alsjava.microservice.configuration;

import org.alsjava.microservice.client.DeviceClient;
import org.alsjava.microservice.client.UserClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class ClientsConfiguration {

    @Bean
    public DeviceClient deviceClient() {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory
                        .builderFor(WebClientAdapter.create(prepareWebClient("")))
                        .build();
        return httpServiceProxyFactory.createClient(DeviceClient.class);
    }

    @Bean
    public UserClient userClient() {
        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory
                        .builderFor(WebClientAdapter.create(prepareWebClient("")))
                        .build();
        return httpServiceProxyFactory.createClient(UserClient.class);
    }

    private WebClient prepareWebClient(String serviceUrl) {
        return WebClient.builder()
                .baseUrl(serviceUrl)
                .build();
    }
}
