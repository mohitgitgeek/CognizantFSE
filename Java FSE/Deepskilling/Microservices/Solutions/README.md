# Microservices hands-on solutions

## Run the account/loan/Eureka/gateway system

From `Solutions`, run `mvn clean package`, then start modules in this order: `discovery-server`, `account-service`, `loan-service`, `api-gateway`. The services register at `http://localhost:8761` and the gateway is on port `9090`.

Endpoints:

- `GET http://localhost:8080/accounts/00987987973432`
- `GET http://localhost:8081/loans/H00987987972342`
- `GET http://localhost:9090/api/accounts/00987987973432`
- `GET http://localhost:9090/api/loans/H00987987972342`

The gateway uses Eureka discovery (`lb://`), logs every request, rewrites `/api` with `StripPrefix`, applies Redis-backed rate limiting to accounts, and returns a Resilience4j circuit-breaker fallback when a downstream service is unavailable. Start Redis if rate limiting is enabled.

## Other PDF exercises

- **User/order system:** expose REST CRUD APIs in two services; inject a `WebClient.Builder` and call the user service from the order service. Persist each bounded context in its own MySQL/PostgreSQL schema.
- **Product/inventory:** use the same discovery setup here with `product-service` and `inventory-service`; add Spring Cloud Config Server for centralized properties.
- **OAuth/OIDC:** add `spring-boot-starter-oauth2-client`, define an OIDC registration in `application.yml`, and use `SecurityFilterChain` with `http.oauth2Login()` (the Spring Boot 3 replacement for `WebSecurityConfigurerAdapter`).
- **Resource server/JWT:** add `spring-boot-starter-oauth2-resource-server`, configure `spring.security.oauth2.resourceserver.jwt.issuer-uri`, and use `http.oauth2ResourceServer(server -> server.jwt())` in the security filter chain. Keep client secrets and JWT keys in environment variables, never in source control.
