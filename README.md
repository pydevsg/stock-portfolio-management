# Stock Portfolio Management System

A full-stack application built with Angular 19 frontend and Spring Boot backend for managing stock portfolios.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Backend Setup](#backend-setup)
  - [Database Configuration](#database-configuration)
  - [Running the Backend](#running-the-backend)
- [Frontend Setup](#frontend-setup)
  - [Angular Configuration](#angular-configuration)
  - [Running the Frontend](#running-the-frontend)
- [Development Workflow](#development-workflow)
- [API Endpoints](#api-endpoints)
- [Troubleshooting](#troubleshooting)
- [Deployment](#deployment)

## Features
- **Portfolio Management**: Add, view, and remove stocks
- **Real-time Data**: Fetch current stock prices
- **Responsive UI**: Built with Angular Material
- **REST API**: Spring Boot backend with JPA
- **Authentication**: JWT security (optional)

## Project Structure

<pre>
stock-portfolio-management/
├── backend/ # Spring Boot Application
│ ├── src/
│ │ ├── main/
│ │ │ ├── java/com/stock/spm/
│ │ │ │ ├── config/ # Security & Web configs
│ │ │ │ ├── controller/ # REST APIs
│ │ │ │ ├── dto/ # Data transfer objects
│ │ │ │ ├── exception/ # Error handling
│ │ │ │ ├── model/ # JPA entities
│ │ │ │ ├── repository/ # Spring Data interfaces
│ │ │ │ ├── service/ # Business logic
│ │ │ │ └── StockPortfolioManageApplication.java
│ │ │ └── resources/
│ │ │ ├── application.properties
│ │ │ └── data.sql # Initial data
│ │ └── test/ # Unit tests
│ └── pom.xml # Maven config
│
├── frontend/ # Angular Application
│ ├── src/
│ │ ├── app/
│ │ │ ├── auth/ # Auth components
│ │ │ ├── stock/ # Stock components
│ │ │ ├── services/ # API services
│ │ │ └── models/ # TS interfaces
│ │ ├── environments/ # Environment configs
│ │ └── styles/ # Global styles
│ └── angular.json # Angular config
└── README.md # This document
</pre>

## Prerequisites

- **Backend**:
  - JDK 17+
  - Maven 3.8+
  - MySQL 8.0+ or H2 database

- **Frontend**:
  - Node.js 20
  - npm 8+ or yarn
  - Angular CLI 19

## Backend Setup

### Database Configuration
1. Edit `backend/src/main/resources/application.properties`:

```
properties
# MySQL Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/stockdb?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=yourpassword

# H2 Configuration (alternative)
# spring.datasource.url=jdbc:h2:mem:stockdb
# spring.datasource.driver-class-name=org.h2.Driver
# spring.h2.console.enabled=true
```
### Running-the-backend
```
cd backend
mvn clean install
mvn spring-boot:run
```

Verify:

API docs: http://localhost:8080/swagger-ui.html

Health check: http://localhost:8080/api/health


## Frontend Setup

Frontend Setup
Angular Configuration
Install dependencies:

bash
cd frontend
npm install

Run on port: http://localhost:4200

### Development Workflow
Start services in separate terminals:

bash
#### Terminal 1 - Backend
``` cd backend && mvn spring-boot:run ```

#### Terminal 2 - Frontend
``` cd frontend && ng serve ```

### API Endpoints

Method	Endpoint	Description
#### GET	/api/stocks	Get all stocks
#### POST	/api/stocks	Add new stock
#### DELETE	/api/stocks/{id}	Remove stock
#### GET	/api/stocks/prices	Fetch latest market prices

Example request 

``` 
POST /api/stocks
{
  "symbol": "MSFT",
  "quantity": 5,
  "price": 329.32
}
```
