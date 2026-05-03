# Kafka Microservices System 🚀

This project demonstrates a production-style event-driven microservices architecture using Spring Boot, Apache Kafka, PostgreSQL, and Docker.

## Architecture

Order Service → Kafka (order-events) → Payment Service → Kafka (payment-events) → Notification Service

## Features

* Event-driven communication using Kafka
* Microservices architecture (3 services)
* PostgreSQL integration (orders, payments, notifications)
* Retry & Dead Letter Topic (DLT) handling
* Dockerized system using docker-compose

## Tech Stack

* Java, Spring Boot
* Apache Kafka
* PostgreSQL
* Docker

## How to Run

```bash
docker compose up --build
```

## Test

```bash
curl -X POST "http://localhost:8081/orders" \
-H "Content-Type: application/json" \
-d '{"orderId":"1","status":"CREATED","amount":500}'
```
