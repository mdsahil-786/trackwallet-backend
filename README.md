# TrackWallet - Personal Finance Tracker Backend

## Overview

TrackWallet is a backend REST API application built using Spring Boot that helps users manage their personal finances efficiently.

Users can:

* Register and log in securely
* Add income and expense transactions
* Categorize transactions
* Edit and delete transactions
* View spending summaries
* Filter transactions
* Access APIs using Swagger documentation

This project was developed as a Personal Finance Tracker assessment project.

---

# Features Implemented

## Authentication

* User Registration
* User Login
* JWT-based Authentication
* Role-based Authorization

---

## Transaction Management

* Create Transactions
* View Transactions
* Update Transactions
* Delete Transactions

---

## Categories

Supported categories include:

* FOOD
* TRAVEL
* SHOPPING
* BILLS
* ENTERTAINMENT
* SALARY
* HEALTH
* OTHER

---

## Dashboard Features

* Total Income
* Total Expense
* Balance Summary

---

## Search & Filters

* Filter by Category
* Filter by Transaction Type
* Filter by Date Range

---

## Pagination & Sorting

* Pagination Support
* Sorting Support

Example:

```http
GET /api/v1/transactions/paged?page=0&size=5&sortBy=amount
```

---

## API Documentation

Swagger/OpenAPI documentation integrated.

Swagger URL:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Tech Stack Used

## Backend

* Java 17
* Spring Boot 3.5
* Spring Security
* Spring Data JPA
* Hibernate

---

## Database

* MySQL

---

## Authentication

* JWT (JSON Web Token)

---

## Documentation

* Swagger / OpenAPI

---

## Build Tool

* Gradle

---

## Utilities

* Lombok
* Dotenv

---

# Project Structure

```text
src/main/java/com/sahil/trackwallet
│
├── configs
├── controllers
├── dto
├── entity
├── enums
├── exceptions
├── mapper
├── repositories
├── security
├── services
```

---

# API Endpoints

## Authentication APIs

### Register

```http
POST /api/v1/auth/register
```

### Login

```http
POST /api/v1/auth/login
```

---

## Transaction APIs

### Create Transaction

```http
POST /api/v1/transactions
```

### Get All Transactions

```http
GET /api/v1/transactions
```

### Update Transaction

```http
PUT /api/v1/transactions/{id}
```

### Delete Transaction

```http
DELETE /api/v1/transactions/{id}
```

---

## Dashboard APIs

### Get Summary

```http
GET /api/v1/transactions/summary
```

---

## Filter APIs

### Filter by Category

```http
GET /api/v1/transactions/category/{category}
```

### Filter by Type

```http
GET /api/v1/transactions/type/{type}
```

### Filter by Date Range

```http
GET /api/v1/transactions/date-range?startDate=2026-05-01&endDate=2026-05-31
```

---

## Pagination API

```http
GET /api/v1/transactions/paged?page=0&size=5&sortBy=createdAt
```

---

# Project Setup Instructions

## 1. Clone Repository

```bash
git clone <your-github-repository-url>
```

---

## 2. Navigate to Project

```bash
cd TrackWallet
```

---

## 3. Configure Environment Variables

Create a `.env` file:

```env
DB_URL=jdbc:mysql://localhost:3306/trackwallet_db
DB_USERNAME=your_username
DB_PASSWORD=your_password

JWT_SECRET=your_jwt_secret
JWT_EXPIRATION=1296000000
```

---

## 4. Configure application.properties

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

server.port=8080

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 5. Run Application

```bash
./gradlew bootRun
```

---

# Testing APIs

You can test APIs using:

* Swagger UI
* Postman

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Future Improvements

* Budget Management
* Monthly Analytics
* Charts & Reports
* Email Notifications
* Docker Deployment
* Frontend Integration

---

# Author

Mohammad Sahil

ABES Engineering College
