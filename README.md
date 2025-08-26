# Souq Backend

A production-ready **Spring Boot** backend for a marketplace app.  
Includes **JWT authentication**, **role-based authorization** (Buyer, Seller, Admin/Manager), product & order APIs, PostgreSQL persistence with **Liquibase** migrations, validation, and CORS configuration for a separate frontend.

---

## ✨ Features

- **Auth**
  - Register/Login with JWT
  - Role-based authorization (RBAC)
  - Password hashing (BCrypt)
- **Catalog**
  - Product CRUD (seller/admin)
  - Public product listing & search
- **Orders**
  - Create orders from cart items
  - Order status updates
- **Cart**
  - Add/Remove/List cart items
  - Scheduled cleanup for expired items
- **Persistence**
  - PostgreSQL with **Liquibase** migrations
- **Quality**
  - Bean validation & global exception handling
  - DTOs & mappers
  - CORS configured for a separate frontend origin

---

## 🧱 Tech Stack

- Java 17+  
- Spring Boot 3 (Web, Security, Validation, Data JPA)  
- PostgreSQL 14+  
- Liquibase  
- JWT (jjwt)  
- Maven (or Gradle)

---

## 📁 Project Structure

