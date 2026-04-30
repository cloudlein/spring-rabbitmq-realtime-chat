# Spring RabbitMQ Realtime Chat

## Documentation
- [Entity Design](doc/entity_design.md)
- [System Architecture](doc/architecture.md)


## Description
A realtime chat application built with Spring Boot, RabbitMQ, and WebSockets. This project demonstrates a decoupled architecture for efficient message delivery and persistence.

## Getting Started
1. Ensure you have Docker installed.
2. Run `docker-compose up -d` to start PostgreSQL and RabbitMQ.
3. Configure your `.env` file based on `env` example.
4. Run the application using `./gradlew bootRun`.