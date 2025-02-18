# Loyalty Service Microservice

The Loyalty Service is a Spring Boot-based microservice designed to manage customer loyalty accounts and their associated loyalty balances. It provides a RESTful API for creating, retrieving, updating, and deleting customer and loyalty account data.

---

## Table of Contents

- [Features](#features)
- [Architecture Overview](#architecture-overview)
- [API Endpoints](#api-endpoints)
    - [Customer Endpoints](#customer-endpoints)
    - [Loyalty Account Endpoints](#loyalty-account-endpoints)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Running the Service](#running-the-service)
- [Error Handling](#error-handling)

---

## Features

- **Customer Management:** Create, update, retrieve, and delete customer records.
- **Loyalty Account Management:** CRUD operations for loyalty accounts, including balance updates.
- **Transactional Operations:** Ensures consistency during balance updates.
- **Optimistic Locking:** Prevents race conditions in concurrent balance updates.
- **RESTful API:** Clean and structured API endpoints for external integrations.

---

## Architecture Overview

The microservice follows a layered architecture:

- **Controllers:**
    - `CustomerController` and `LoyaltyAccountController` expose REST endpoints.

- **Services:**
    - `CustomerService` and `LoyaltyAccountService` encapsulate business logic and interact with repositories.

- **Repositories:**
    - Spring Data JPA repositories for persistence operations.

- **Models:**
    - Entity classes representing Customer and LoyaltyAccount, along with necessary enums.

The service uses **Jakarta Persistence (JPA)** for ORM and **Spring Boot** for rapid application development.

---

## API Endpoints

### Customer Endpoints

| Method | Endpoint          | Description                                  |
| ------ | ----------------- | -------------------------------------------- |
| GET    | `/v1/customer`      | Retrieve a list of all customers.            |
| GET    | `/v1/customer/{id}` | Retrieve details of a specific customer by ID. |
| POST   | `/v1/customer`      | Create a new customer.                       |
| PATCH  | `/v1/customer/{id}` | Update an existing customer.                 |
| DELETE | `/v1/customer/{id}` | Delete a customer by ID.                     |

---

### Loyalty Account Endpoints

| Method | Endpoint                     | Description                                        |
| ------ | ---------------------------- | -------------------------------------------------- |
| GET    | `/v1/loyalty`                 | Retrieve all loyalty accounts.                     |
| GET    | `/v1/loyalty/{id}`            | Retrieve details of a specific loyalty account.   |
| PATCH  | `/v1/loyalty/{id}`            | Update an existing loyalty account (balance, level). |
| DELETE | `/v1/loyalty/{id}`            | Delete a loyalty account by ID.                    |

---

## Prerequisites

Before running the Loyalty Service, ensure you have the following installed:

- **Java:** JDK 17 or higher
- **Build Tool:** Maven
- **Database:** PostgreSQL
- **IDE:** Optional (e.g., IntelliJ IDEA, Eclipse) for development

---

## Installation

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/Matt-Hays/loyalty-program-service.git
   cd loyalty-service
   ```

2. **Build the Service:**

   ```bash
   mvn clean install
   ```

---

## Running the Service

1. **Run with Maven:**

   ```bash
   mvn spring-boot:run
   ```

2. **Run with Docker:**

   ```bash
   mvn clean spring-boot:build-image -DskipTests
   docker run -p 8081:8081 docker.io/library/loyalty-service:0.0.1-SNAPSHOT
   ```

---

## Error Handling
The microservice handles errors using HTTP response codes:
- **404 Not Found** - Resource does not exist
- **400 Bad Request** - Invalid input data
- **422 Unprocessable Entity** - Optimistic locking issues
- **500 Internal Server Error** - General server errors

