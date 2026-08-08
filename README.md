# Deepskilling Hands-on Solutions

This repository contains organized, source-controlled solutions for the Digital Nurture Java FSE Deepskilling exercises. Original handouts are preserved separately; solution code and instructions live in the relevant module folders.

## Completed modules

- **Engineering Concepts** — Java implementations covering inventory management, searching, sorting, arrays, linked lists, recursion, and design-pattern examples.
- **PL/SQL Programming** — Oracle SQL/PLSQL exercises for control structures, safe fund transfers, stored procedures, functions, triggers, cursors, and packages.
- **Spring Core and Maven** — Maven library-management project with XML and annotation configuration, dependency injection, and AOP logging.
- **Spring REST using Spring Boot** — REST APIs for country, employee, and department operations with validation and centralized error handling.
- **Spring Data JPA with Hibernate** — employee-management project with entities, relationships, repositories, JPQL, paging, sorting, auditing, projections, and batch settings.
- **React** — [`Java FSE/Deepskilling/React/Solutions`](Java%20FSE/Deepskilling/React/Solutions) contains a Vite React project demonstrating components, props, state, JSX, events, conditional rendering, context, forms, and validation.
- **GIT** — [`Java FSE/Deepskilling/GIT/Solutions`](Java%20FSE/Deepskilling/GIT/Solutions) documents initialization, commits, `.gitignore`, branching, conflict resolution, cleanup, and push workflows.
- **JUnit, Mockito and SLF4J** — [`Java FSE/Deepskilling/JUnit,%20Mockito%20and%20SL4J/Solutions`](Java%20FSE/Deepskilling/JUnit%2C%20Mockito%20and%20SL4J/Solutions) contains a Maven project with JUnit 5 tests, Mockito stubbing/verifications/captors, and SLF4J logging.
- **Microservices** — [`Java FSE/Deepskilling/Microservices/Solutions`](Java%20FSE/Deepskilling/Microservices/Solutions) provides Account and Loan REST services, a Eureka discovery server, and an API Gateway with routing, logging, load balancing, rate limiting, circuit breakers, and fallbacks.

## Running the solutions

- React: enter `React/Solutions/react-hands-on`, run `npm install`, then `npm run dev`.
- JUnit/Mockito/SLF4J: enter its `Solutions` folder and run `mvn test`.
- Microservices: enter `Microservices/Solutions`, run `mvn clean package`, then start `discovery-server`, `account-service`, `loan-service`, and `api-gateway` in that order. See its README for endpoint details.

## Repository

- GitHub: https://github.com/mohitgitgeek/CognizantFSE
- Branch: `main`

Generated build outputs and dependencies are intentionally excluded from version control.
