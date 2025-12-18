# Shop Project

## Overview

This project implements a **price management system for an e-commerce platform**. It allows querying the applicable price for a product based on:

- Application date
- Product identifier
- Brand identifier

The system determines the correct price according to the defined parameters, productId, brandId, and application date (must be within a date range).

## Technology Stack

- **Java**
- **Maven**
- **Spring Boot**
- **H2 Database** (in-memory, initialized with sample data on startup)
- **MapStruct** (object mapping)
- **OpenAPI / Swagger** (API documentation)

## Architecture

The project follows a **Hexagonal Architecture (Ports & Adapters)** approach, promoting separation of concerns, testability, and maintainability.

### Directory Structure

```
└── src/main/java
    └── com/example/shop
        ├── application
        │   └── usecase
        ├── domain
        │   ├── model
        │   ├── port
        │   └── service
        └── infrastructure
            ├── database
            │   ├── adapter
            │   ├── entity
            │   ├── mapper
            │   └── repository
            └── rest
                ├── controller
                └── mapper
```

### Layer Description

- **Application**: Contains use cases that orchestrate application logic.
- **Domain**: Core business logic, domain models, services, and ports (interfaces).
- **Infrastructure**: Technical details such as database access, REST controllers, persistence entities, and mappers.

## Project Structure

- `src/main/java`: Main source code (models, services, use cases, adapters).
- `src/test/java`: Unit and integration tests.
- `src/main/resources`: Configuration files and resources.

## Database

- Uses **H2 in-memory database**.
- Sample data is automatically inserted when the application starts.
- No external database setup is required.

## API Documentation

The project exposes its REST API using **OpenAPI** to define the contract.


The OpenAPI specification is located at:
```
src/main/resources/openapi.yml
```
During the build process, the OpenAPI definition is used to generate the related API classes automatically.

This ensures the implementation remains aligned with the API contract and reduces manual boilerplate code.


## How to Run the Project

### 1. Clone the Repository

```
git clone https://github.com/gonperezp/shop.git
cd shop
```

### 2. Build the Project

```
mvn clean compile
```

### 3. Run Tests

```
mvn test
```

### 4. Start the Application

```
mvn spring-boot:run
```

The application will start on port **8080**.

## Example Request

You can query the applicable price using the following `curl` command:

```
curl "http://localhost:8080/price?applicationDate=2020-06-14%2016:00:00&productId=35455&brandId=1"
```

### Request Parameters

- `applicationDate`: Date and time when the price should be applied (`yyyy-MM-dd HH:mm:ss`)
- `productId`: Product identifier
- `brandId`: Brand identifier

### Example Response

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```

## Testing

- Unit tests validate domain logic and services.
- Integration tests verify REST endpoints and database interaction.

## Notes

- The H2 database is reset every time the application starts.




