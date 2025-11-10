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

bash
### 1️⃣ Clone the repository
 git clone https://github.com/sudeshjadhavv/vehicle-service-management-system.git
### 2️⃣ Navigate to the project directory
cd vehicle-service-management-system

### 🗄️ Database Setup Guide

Follow these steps to configure and connect your MySQL database with the Vehicle Service Management System:

### 3️⃣ Create the Database
```sql
CREATE DATABASE vehicle_service_management;
```
###  Configure application.properties
```
spring.datasource.url=jdbc:mysql://localhost:3306/vehicle_service_management
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
### JWT Secret (use any random string)
```
app.jwt-secret=your_jwt_secret_key
app.jwt-expiration=86400000
```
### 4️⃣ Run the Application
```
bash
mvn spring-boot:run
```
### 5️⃣ Verify Database Tables

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

###  Insert Sample Data (Admin, Customer, Mechanic)

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


## 🔐 Authentication Workflow

1. **Register User** → `/api/auth/register`  
   User details (name, email, password, role) are saved in the database (passwords are encrypted).

2. **Login User** → `/api/auth/login`  
   Returns a **JWT token** upon successful login.

3. **Use JWT Token in Postman:**  
   - Copy the token from the login response.  
   - Go to the **Authorization** tab in Postman.  
   - Choose **Bearer Token** and paste your token.  
   - Now you can access all secured endpoints.

---

## 📡 API Endpoints

### 🔑 Authentication

| Method | Endpoint | Description |
|:-------:|:----------|:-------------|
| POST | `/api/auth/register` | Register a new user (Admin, Customer, Mechanic) |
| POST | `/api/auth/login` | Login and get JWT token |

---

### 👤 Users

| Method | Endpoint | Description | Access |
|:-------:|:----------|:-------------|:---------|
| GET | `/api/users` | Get all users | Admin |
| GET | `/api/users/{id}` | Get user by ID | Admin |
| DELETE | `/api/users/{id}` | Delete user | Admin |

---

### 🚗 Vehicles

| Method | Endpoint | Description | Access |
|:-------:|:----------|:-------------|:---------|
| GET | `/api/vehicles` | Get all vehicles | Admin |
| GET | `/api/vehicles/{id}` | Get vehicle by ID | Admin/Customer |
| GET | `/api/vehicles?ownerId={ownerId}` | Get vehicles by owner ID | Customer |
| POST | `/api/vehicles?ownerId={ownerId}` | Add a new vehicle for a customer | Admin/Customer |
| PUT | `/api/vehicles/{id}` | Update vehicle details | Admin |
| DELETE | `/api/vehicles/{id}` | Delete vehicle | Admin |

---

### 🧾 Service Requests

| Method | Endpoint | Description | Access |
|:-------:|:----------|:-------------|:---------|
| GET | `/api/requests` | Get all service requests | Admin |
| GET | `/api/requests/{id}` | Get service request by ID | Admin/Customer/Mechanic |
| GET | `/api/requests?status={status}` | Filter service requests by status | Admin/Mechanic |
| POST | `/api/requests?vehicleId={vehicleId}` | Create new service request for a vehicle | Customer |
| PUT | `/api/requests/{id}` | Update service request details | Admin/Mechanic |
| POST | `/api/requests/{requestId}/assign?mechanicId={mechanicId}` | Assign mechanic to a request | Admin |
| DELETE | `/api/requests/{id}` | Delete service request | Admin |

---

## 🧪 Testing with Postman

### 1️⃣ Register a User
**POST** → `http://localhost:8080/api/auth/register`  
**Body (JSON):**
```json
{
  "name": "Rahul Sharma",
  "email": "rahul@gmail.com",
  "password": "123456",
  "role": "CUSTOMER"
}
```
### 2️⃣ Login User

**POST** → http://localhost:8080/api/auth/login
**Body (JSON):**
```json
{
  "email": "rahul@gmail.com",
  "password": "123456"
}
```
### 3️⃣ Add a Vehicle

**POST** → http://localhost:8080/api/vehicles?ownerId=1
**Body (JSON):**
```json
{
  "model": "Swift",
  "licensePlate": "MH12AB1234"
}
```
### 4️⃣ Create a Service Request

**POST** → http://localhost:8080/api/requests?vehicleId=1
**Body (JSON):**
```json
{
  "description": "Oil change and tire rotation"
}
```
### 5️⃣ Assign Mechanic to Request (Admin only)

**POST** → http://localhost:8080/api/requests/1/assign?mechanicId=2

### 6️⃣ Update Service Status (Mechanic only)

**PUT** → http://localhost:8080/api/requests/1
**Body (JSON):**
```json
{
  "status": "COMPLETED"
}
```
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

## 🏁 Project Conclusion

The **Vehicle Service Management System** project demonstrates the ability to design and develop a **secure, real-world backend application** using **Java Spring Boot**.  
It effectively integrates **JWT-based authentication**, **role-based access control**, and **RESTful API design** while maintaining clear modular separation of concerns.  
This project highlights a strong understanding of **Spring Security**, **JPA/Hibernate ORM**, and **database management** using **MySQL**.  

Overall, it showcases backend development skills that are crucial for **enterprise-level applications**, including maintainability, scalability, and security.

---

## 🚀 Future Improvements

Here are some ideas and planned enhancements to make the Vehicle Service Management System more powerful and production-ready:

1. **🧭 Frontend Integration (React/Angular):**  
   Build a user-friendly frontend interface for customers, mechanics, and admins.

2. **📱 Customer Notifications:**  
   Send SMS or email updates when a vehicle service request is created, assigned, or completed.

3. **💳 Online Payment Integration:**  
   Allow customers to pay online for services directly through the portal.

4. **📊 Admin Dashboard & Analytics:**  
   Add graphical reports showing service trends, mechanic performance, and revenue insights.

5. **🧰 Advanced Role Management:**  
   Introduce more granular permissions (e.g., sub-admin, workshop manager).

6. **🗓️ Service Scheduling & Reminders:**  
   Enable customers to book time slots and receive service reminders automatically.

7. **☁️ Cloud Deployment:**  
   Deploy the backend on AWS, Azure, or Render with CI/CD pipelines for continuous delivery.

8. **🧪 Unit & Integration Testing:**  
   Add JUnit and Mockito-based tests for improved code reliability and maintainability.

---

💡 *These improvements will enhance scalability, maintainability, and user experience, making the system ready for real-world deployment.*

## 📬 Contact Details

**👤 Developer:** Sudesh Jadhav  
**📧 Email:** *(sudeshjadhavv@gmail.com)*    
**💻 GitHub:** [github.com/sudeshjadhavv](https://github.com/sudeshjadhavv)

---

⭐ *If you like this project, consider giving it a star on GitHub!* ⭐

