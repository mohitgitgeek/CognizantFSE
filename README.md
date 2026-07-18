# Microservices Composite Hands-on

This workspace contains three Spring Boot microservices implementing the exercise from the handout:

- account: exposes GET /accounts/{number}
- loan: exposes GET /loans/{number}
- composite: exposes GET /composite/{number} and calls the other two services

## Run locally

1. Start the account service:
   - cd account
   - mvn spring-boot:run

2. Start the loan service:
   - cd loan
   - mvn spring-boot:run

3. Start the composite service:
   - cd composite
   - mvn spring-boot:run

## Test endpoints

- http://localhost:8081/accounts/00987987973432
- http://localhost:8082/loans/H00987987972342
- http://localhost:8083/composite/00987987973432
