# How To configure environment variables in IntelliJ

## Variables

- EUREKA_URL=http://localhost:7001/eureka
- KAFKA_URL=localhost:9092
- REDIS_URL=localhost
- KEYCLOAK_URL=http://localhost:28080/realms/SpringBootKeycloak

1. Open the Project Configuration ![Clonación](Intellij-Environment-Variables-00.png?raw=true)
2. Click on "Modify Options" ![Clonación](Intellij-Environment-Variables-01.png?raw=true)
3. Select "Environment variables" ![Clonación](Intellij-Environment-Variables-02.png?raw=true)
4. Lick on step 1 and create your variables ![Clonación](Intellij-Environment-Variables-03.png?raw=true)

# Notes

Apply this option to **"ConfigurationService"**, **"UserService"**, **"DeviceService"**, **"GatewayService"**, **"
GraphQLService"**