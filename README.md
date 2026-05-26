# Enterprise Project Management System

## Overview

Enterprise Project Management System is a backend REST API application built using Spring Boot. The system helps organizations manage projects, tasks, users, and task comments efficiently with a clean layered architecture.

This project demonstrates enterprise-level backend development concepts such as:

* RESTful API Design
* Layered Architecture
* DTO Pattern
* Global Exception Handling
* Validation
* Audit Logging
* Swagger API Documentation
* Spring Data JPA
* MySQL Database Integration
* Service & Repository Pattern

---

# Tech Stack

## Backend

* Java 17
* Spring Boot 3.3.5
* Spring Web
* Spring Data JPA
* Spring Validation
* Spring AOP
* Spring Actuator

## Database

* MySQL

## Documentation

* Swagger / OpenAPI

## Build Tool

* Maven

## Additional Libraries

* Lombok
* ModelMapper

---

# Project Structure

```bash
src/main/java/com/enterpriseassignment
│
├── audit/               # Audit related classes
├── config/              # Configuration classes
├── controller/          # REST Controllers
├── dto/                 # Request & Response DTOs
├── entity/              # JPA Entity classes
├── enums/               # Enum constants
├── exception/           # Custom exception handling
├── repository/          # JPA repositories
├── service/             # Service interfaces
├── service/impl/        # Service implementations
├── util/                # Utility classes
└── ProjectManagementApplication.java
```

---

# Features

## User Management

* Create Users
* Update Users
* Delete Users
* Fetch User Details

## Project Management

* Create Projects
* Update Project Details
* Delete Projects
* Get Project Information

## Task Management

* Create Tasks
* Assign Tasks
* Update Task Status
* Track Task Progress
* Delete Tasks

## Task Comments

* Add Comments to Tasks
* Fetch Task Comments
* Delete Comments

## Dashboard APIs

* Project Statistics
* Task Summary
* User Activity Overview

---

# API Modules

| Module        | Description                  |
| ------------- | ---------------------------- |
| User API      | Handles user operations      |
| Project API   | Manages projects             |
| Task API      | Handles task operations      |
| Comment API   | Manages task comments        |
| Dashboard API | Provides dashboard analytics |

---

# Database Configuration

Update the following properties inside:

```properties
src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/project_management
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

# Running the Application

## Clone Repository

```bash
git clone https://github.com/your-username/ProjectManagementSystem.git
```

## Navigate to Project

```bash
cd ProjectManagementSystem
```

## Build Project

```bash
mvn clean install
```

## Run Application

```bash
mvn spring-boot:run
```

Application will start on:

```bash
http://localhost:8080
```

---

# Swagger Documentation

After running the application:

```bash
http://localhost:8080/swagger-ui/index.html
```

---

# Sample API Endpoints

## User APIs

| Method | Endpoint        |
| ------ | --------------- |
| POST   | /api/users      |
| GET    | /api/users      |
| GET    | /api/users/{id} |
| PUT    | /api/users/{id} |
| DELETE | /api/users/{id} |

## Project APIs

| Method | Endpoint           |
| ------ | ------------------ |
| POST   | /api/projects      |
| GET    | /api/projects      |
| GET    | /api/projects/{id} |
| PUT    | /api/projects/{id} |
| DELETE | /api/projects/{id} |

## Task APIs

| Method | Endpoint               |
| ------ | ---------------------- |
| POST   | /api/tasks             |
| GET    | /api/tasks             |
| GET    | /api/tasks/{id}        |
| PUT    | /api/tasks/{id}        |
| DELETE | /api/tasks/{id}        |
| PATCH  | /api/tasks/{id}/status |

---

# Validation & Exception Handling

The application uses:

* Bean Validation
* Custom Exceptions
* Global Exception Handler
* Standard API Response Structure

Example Response:

```json
{
  "success": true,
  "message": "Task created successfully",
  "data": {
    "id": 1,
    "title": "Complete Backend Module"
  },
  "timestamp": "2026-05-26T10:00:00"
}
```

---

# Audit Logging

The system contains audit support for:

* Created Date
* Updated Date
* Entity Tracking

Audit functionality is implemented using:

* JPA Entity Listeners
* AuditEntity Base Class

---

# Actuator Endpoints

Spring Boot Actuator is enabled for monitoring.

Example:

```bash
http://localhost:8080/actuator/health
```

---

# Maven Dependencies Used

* spring-boot-starter-web
* spring-boot-starter-data-jpa
* spring-boot-starter-validation
* spring-boot-starter-aop
* spring-boot-starter-actuator
* mysql-connector-j
* modelmapper
* lombok
* springdoc-openapi

---
