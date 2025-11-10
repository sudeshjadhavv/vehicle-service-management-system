# 🚗 Vehicle Service Management System

A **Spring Boot**-based backend application for managing vehicle servicing operations such as customer registration, vehicle management, service booking, mechanic assignment, and tracking service status — all secured with **JWT authentication** and **role-based authorization**.

---

## 🧰 Tech Stack

| Layer | Technology |
|:------|:------------|
| **Language** | Java 17 |
| **Framework** | Spring Boot |
| **ORM** | Hibernate / JPA |
| **Database** | MySQL |
| **Security** | Spring Security with JWT |
| **Build Tool** | Maven |
| **API Testing** | Postman / Swagger |
| **Version Control** | Git & GitHub |

---

## 🌟 Key Features

✅ **User Authentication & Authorization** — Login/Register using JWT-based authentication with roles like `ADMIN`, `CUSTOMER`, and `MECHANIC`.

✅ **Role-Based Access Control** —  
- `ADMIN` → Manage users, vehicles, and assign mechanics  
- `CUSTOMER` → Register vehicles and create service requests  
- `MECHANIC` → View and update assigned service requests  

✅ **Service Management** — Create, update, and track service requests with real-time status (PENDING, IN_PROGRESS, COMPLETED).

✅ **Vehicle Management** — Add and manage multiple vehicles per customer.

✅ **Database Integration** — Persistent data storage using Hibernate and MySQL.

✅ **RESTful APIs** — Clean, modular API design for easy integration with frontend apps.

✅ **Exception Handling & Validation** — Robust input validation and centralized error handling.

---

### 📂 Project Structure

```
Vehicle_Service_Management_System
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.vehicleservice
│   │   │       ├── config
│   │   │       │   └── SecurityConfig.java
│   │   │       │       → Configures Spring Security and JWT authentication.
│   │   │       │
│   │   │       ├── Controllers
│   │   │       │   ├── AuthController.java
│   │   │       │   ├── VehicleController.java
│   │   │       │   ├── ServiceRequestController.java
│   │   │       │   └── UserController.java
│   │   │       │       → Handles incoming REST API requests and maps them to service layer methods.
│   │   │       │
│   │   │       ├── entities
│   │   │       │   ├── User.java
│   │   │       │   ├── Vehicle.java
│   │   │       │   └── ServiceRequest.java
│   │   │       │       → Represents database entities and their relationships (JPA/Hibernate).
│   │   │       │
│   │   │       ├── Repositories
│   │   │       │   ├── UserRepository.java
│   │   │       │   ├── VehicleRepository.java
│   │   │       │   └── ServiceRequestRepository.java
│   │   │       │       → Interfaces for database access using Spring Data JPA.
│   │   │       │
│   │   │       ├── security
│   │   │       │   ├── CustomerUserDetailService.java
│   │   │       │   ├── JwtAuthenticationEntryPoint.java
│   │   │       │   ├── JwtAuthenticationFilter.java
│   │   │       │   └── JwtTokenProvider.java
│   │   │       │       → Manages JWT generation, validation, and authentication handling.
│   │   │       │
│   │   │       └── service
│   │   │           ├── AuthService.java
│   │   │           ├── AuthServiceImpl.java
│   │   │           ├── VehicleService.java
│   │   │           ├── RequestService.java
│   │   │           └── UserService.java
│   │   │               → Contains business logic and communication between controllers and repositories.
│   │   │
│   │   └── resources
│   │       ├── application.properties
│   │       └── data.sql
│   │           → Configuration files and initial database data.
│   │
│   └── test
│       └── java
│           → Contains unit and integration tests.
│
├── pom.xml           → Maven project configuration and dependencies.
├── HELP.md           → Spring Boot help file.
├── mvnw / mvnw.cmd   → Maven wrapper scripts.
└── target/           → Compiled output and build files.
```

## 🧭 System Architecture

The **Vehicle Service Management System** follows a layered architecture with the following components:

- **Controller Layer** → Handles incoming HTTP requests and responses.  
- **Service Layer** → Contains business logic and communicates with repositories.  
- **Repository Layer** → Manages database operations using Spring Data JPA.  
- **Security Layer** → Handles JWT-based authentication and authorization.  
- **Entity Layer** → Defines database models (Employee, Department, Role, User).  

This architecture ensures **scalability, maintainability, and clear separation of concerns.**

---

## ⚙️ Setup & Installation

### 🪜 Prerequisites
Before running this project, make sure you have:

- Java 17+  
- Maven 3.9+  
- MySQL 8+  
- Postman (optional, for API testing)

---

### 🧩 Steps to Run Locally

1. **Clone the repository**
   ```bash
   git clone https://github.com/sudeshjadhavv/vehicle-service-management-system.git
   cd vehicle-service-management-system

### 🗄️ Database Setup Guide

Follow these steps to configure and connect your MySQL database with the Vehicle Service Management System:

### 1️⃣ Create the Database
```sql
CREATE DATABASE vehicle_service_management;
```
### 2️⃣ Configure application.properties
```
spring.datasource.url=jdbc:mysql://localhost:3306/vehicle_service_management
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
### 3️⃣ Run the Application
```
bash
mvn spring-boot:run
```
### 4️⃣ Verify Database Tables

Once the application starts successfully, open **MySQL Workbench** and verify that these tables are created:

```bash
SHOW TABLES;
+-------------------+
| Tables_in_vehicle_service_management |
+-------------------+
| users             |
| vehicles          |
| service_requests  |
+-------------------+
```
  ### 🗄️ Database Design
```
┌────────────┐       ┌──────────────┐       ┌────────────────────┐
│   USERS    │1     M│   VEHICLES   │1     M│  SERVICE_REQUESTS  │
│────────────│-------│──────────────│-------│────────────────────│
│ id (PK)    │       │ id (PK)      │       │ id (PK)            │
│ name       │       │ model        │       │ description        │
│ email      │       │ licensePlate │       │ status             │
│ password   │       │ owner_id (FK)│       │ vehicle_id (FK)    │
│ role       │       │              │       │ mechanic_id (FK)   │
└────────────┘       └──────────────┘       └────────────────────┘
```

### 5️⃣ Insert Sample Data (Admin, Customer, Mechanic)

After the database and tables are ready, insert sample users and roles to test the system.

```sql
-- INSERT SAMPLE USERS
INSERT INTO users (name, email, password, role) VALUES
('Admin User', 'admin@example.com', 'admin@123', 'ADMIN'),
('John Doe', 'john@example.com', 'john@123', 'CUSTOMER'),
('Ravi Mechanic', 'ravi@example.com', 'ravi@123', 'MECHANIC'),
('Priya Mechanic', 'priya@example.com', 'priya@123', 'MECHANIC');

-- INSERT SAMPLE VEHICLES
INSERT INTO vehicles (model, license_plate, owner_id) VALUES
('Honda City', 'MH12AB1234', 2),
('Maruti Swift', 'MH14CD5678', 2);

-- INSERT SAMPLE SERVICE REQUESTS
INSERT INTO service_requests (description, status, vehicle_id, mechanic_id) VALUES
('Oil Change and Filter Replacement', 'PENDING', 1, 3),
('Brake Check and Replacement', 'IN_PROGRESS', 2, 4),
('Engine Diagnostics', 'COMPLETED', 1, 3);

```
### 🔍 Notes

- Passwords here are **plain text** only for testing (in production, they should be **encrypted using BCrypt**).
- Roles help control **authorization** (e.g., only Admin can manage users, only Mechanics can update service status).
- You can execute these commands directly in **MySQL Workbench** or **phpMyAdmin** after creating the database.


## 6️⃣ 🚀 API Endpoints Documentation

Below are the main RESTful API endpoints for the **Vehicle Service Management System**.  
Use **Postman** to test each API.

| Category     | Method | Endpoint                             | Role     | Description                |
| ------------ | ------ | ------------------------------------ | -------- | -------------------------- |
| **Auth**     | POST   | `/api/auth/register`                 | ALL      | Register new user          |
|              | POST   | `/api/auth/login`                    | ALL      | Login and get JWT token    |
| **Customer** | POST   | `/api/customer/vehicles`             | CUSTOMER | Add new vehicle            |
|              | POST   | `/api/customer/requests`             | CUSTOMER | Create new service request |
| **Admin**    | GET    | `/api/admin/requests`                | ADMIN    | View all service requests  |
|              | POST   | `/api/admin/requests/{id}/assign`    | ADMIN    | Assign mechanic            |
| **Mechanic** | PUT    | `/api/mechanic/requests/{id}/status` | MECHANIC | Update service status      |

## 🧪 Testing in Postman

1️⃣ **Register or Login**  
   - First, send a `POST` request to register or log in.  
   - You will receive a **JWT token** in the login response.

2️⃣ **Copy the Token**  
   - Copy the token value from the login response body.

3️⃣ **Add Token in Postman**  
   - In Postman, go to the **Authorization** tab.  
   - Choose **Bearer Token** as the type.  
   - Paste your token into the token field.

4️⃣ **Access Secured Endpoints**  
   - Now you can successfully access all secured API endpoints.  
   - If the token expires, log in again to get a new token.


## 🏁 Project Conclusion

The **Vehicle Service Management System** project demonstrates the ability to design and develop a **secure, real-world backend application** using **Java Spring Boot**.  
It effectively integrates **JWT-based authentication**, **role-based access control**, and **RESTful API design** while maintaining clear modular separation of concerns.  
This project highlights a strong understanding of **Spring Security**, **JPA/Hibernate ORM**, and **database management** using **MySQL**.  

Overall, it showcases backend development skills that are crucial for **enterprise-level applications**, including maintainability, scalability, and security.

---

## 📬 Contact Details

**👤 Developer:** Sudesh Jadhav  
**📧 Email:** *(sudeshjadhavv@gmail.com)*    
**💻 GitHub:** [github.com/sudeshjadhavv](https://github.com/sudeshjadhavv)

---

⭐ *If you like this project, consider giving it a star on GitHub!* ⭐

