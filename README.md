# TrackWallet — Personal Finance Tracker Backend

TrackWallet is a production-ready backend application built using Spring Boot that helps users manage personal finances through secure REST APIs.

The application provides features such as JWT authentication, transaction management, filtering, pagination, transaction summaries, and Swagger API documentation.

This project was built to practice backend engineering concepts including authentication, authorization, API design, database integration, deployment, and cloud hosting.

---

# Live Deployment

## Production API

https://enchanting-playfulness-production-42cf.up.railway.app

## Swagger Documentation

https://enchanting-playfulness-production-42cf.up.railway.app/swagger-ui/index.html

---

# Features

## Authentication & Security

* User Registration
* User Login
* JWT-based Authentication
* Role-based Authorization
* BCrypt Password Encryption
* Stateless Session Management using Spring Security

---

## Transaction Management

Users can:

* Create Transactions
* View Transactions
* Update Transactions
* Delete Transactions

Each transaction supports:

* title
* amount
* category
* transaction type
* description
* transaction date

---

## Transaction Categories

Supported categories:

* FOOD
* TRAVEL
* SHOPPING
* BILLS
* ENTERTAINMENT
* SALARY
* HEALTH
* OTHER

---

## Dashboard & Summary APIs

The application provides transaction summary APIs including:

* Total Income
* Total Expense
* Current Balance

---

## Search & Filtering

Users can filter transactions using:

* Category
* Transaction Type
* Date Range

Example:


GET /api/v1/transactions/type/EXPENSE



GET /api/v1/transactions/category/FOOD



GET /api/v1/transactions/date-range?startDate=2026-05-01&endDate=2026-05-31


---

## Pagination & Sorting

Pagination support has been implemented for large datasets.

Example:


GET /api/v1/transactions/paged?page=0&size=5&sortBy=amount


---

# Tech Stack

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

## Deployment & DevOps

* Railway Deployment
* Docker-based Deployment

---

## Build Tool

* Gradle

---

## Additional Libraries

* Lombok
* JJWT

---

# Project Architecture


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

---

# API Endpoints

# Authentication APIs

## Register User


POST /api/v1/auth/register


## Login User


POST /api/v1/auth/login


---

# Transaction APIs

## Create Transaction


POST /api/v1/transactions


## Get All Transactions


GET /api/v1/transactions


## Update Transaction


PUT /api/v1/transactions/{id}


## Delete Transaction


DELETE /api/v1/transactions/{id}


---

# Summary APIs

## Get Financial Summary


GET /api/v1/transactions/summary


---

# Filter APIs

## Filter by Category


GET /api/v1/transactions/category/{category}


## Filter by Transaction Type


GET /api/v1/transactions/type/{type}


## Filter by Date Range

GET /api/v1/transactions/date-range?startDate=2026-05-01&endDate=2026-05-31


---

# Pagination API


GET /api/v1/transactions/paged?page=0&size=5&sortBy=createdAt


---

# Authentication Flow

1. User registers using the register API
2. User logs in using credentials
3. Server generates JWT token
4. Client sends JWT token in Authorization header
5. JwtAuthFilter validates the token
6. Protected APIs become accessible

---

# Security Implementation

* JWT Token Authentication
* Stateless Authentication
* BCrypt Password Encoding
* Protected REST APIs
* Role-based Authorization
* Spring Security Filter Chain

---

# Swagger Documentation

Swagger UI has been integrated for API testing and documentation.

Swagger URL:


https://enchanting-playfulness-production-42cf.up.railway.app/swagger-ui/index.html


Features:

* API Testing
* JWT Authorization Support
* Request/Response Models
* Interactive API Documentation

---

# Local Setup Instructions

## 1. Clone Repository


git clone <your-github-repository-url>


---

## 2. Navigate to Project Directory


cd TrackWallet


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

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

server.port=8080

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


---

## 5. Run Application


./gradlew bootRun


---

# API Testing

The APIs can be tested using:

* Swagger UI
* Postman

Swagger:


https://enchanting-playfulness-production-42cf.up.railway.app/swagger-ui/index.html


---

# Future Improvements

Planned improvements for future versions:

* Budget Management
* Monthly Analytics
* Charts & Reports
* Email Notifications
* Redis Caching
* Refresh Token Authentication
* Microservices Architecture
* Frontend Integration

---

# Author

## Mohammad Sahil

Backend Developer | Java | Spring Boot | REST APIs | JWT Authentication

