# Rentify Backend

Rentify is a web application designed to facilitate renting and managing properties. This repository contains the backend implementation of Rentify, built with Spring Boot.

## Features

- *User Authentication*: Allows users to register, login, and manage their profiles.
- *Property Management*: Sellers can post, update, and delete their properties. Buyers can view available properties.
- *Interest Management*: Buyers can express interest in properties, and sellers receive notifications.
- *Security*: JWT-based authentication and authorization. Data security using Spring Security.

## Tech Stack

- *Spring Boot*: For creating the backend RESTful API.
- *Spring Data JPA*: For database operations.
- *Spring Security*: For authentication and authorization.
- *PostgreSQL*: As the database.
- *JWT (JSON Web Tokens)*: For authentication.
- *Maven*: As the build tool.
- *JUnit 5*: For unit and integration testing.
- *Swagger*: For API documentation.

## Prerequisites

- Java 11
- Maven
- PostgreSQL
- IDE (IntelliJ IDEA, Eclipse, etc.)

## Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your_username/rentify-backend.git
cd rentify-backend

### 2. ~Set up PostgreSQL~
1.Create a PostgreSQL database named rentify_db.
2.Update src/main/resources/application.properties with your database username and password:
spring.datasource.url=jdbc:postgresql://localhost:5432/rentify_db
spring.datasource.username=your_username
spring.datasource.password=your_password

### 3. Run the application
1.Open the project in your IDE and run RentifyApplication.java.
2.Or, run the following Maven command:
mvn spring-boot:run

###4. Access the application
1.The backend will start on 'http://localhost:8080'
API Documentation
1.Swagger UI: http://localhost:8080/swagger-ui.html
 ~This page describes the API endpoints, request parameters, and responses.
