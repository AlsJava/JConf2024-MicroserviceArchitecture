package org.alsjava.microservice.configuration;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * Example connection: jdbc:h2:tcp://localhost:11000/mem:DeviceDB
 */
@ConditionalOnProperty(prefix = "db.h2.debug", name = "enabled", havingValue = "true")
@Configuration(proxyBeanMethods = false)
public class H2MemoryTcpDBConfiguration {

    @Value("${db.h2.debug.port]")
    private int h2DebugPort;

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseaServer() throws SQLException {
        return Server.createTcpServer(  "-tcp", "-tcpAllowOthers", "-tcpPort", String.valueOf(h2DebugPort));
    }
}
