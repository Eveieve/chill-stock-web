# Chill Stock

**Chill Stock** is a **warehouse management system for fresh goods**, designed and developed to explore and practice real-world backend engineering skills.

This project focuses on building a clean backend architecture while learning how to connect all the necessary components in a professional-grade web application.

While I am still growing as a backend developer, this project reflects my understanding of clean backend design, domain separation, data flow, and handling realistic challenges in multi-user environments.

---

## 📦 Project Overview

The application simulates core warehouse operations:

- User registration, login (session-based), and role-based access control
- Admin management for inbound (stock in), outbound (stock out), and inventory
- Clear separation between admin and user functions
- Record and history management with SQL triggers and logs

---

## 🛠 Tech Stack

- **Spring Framework** (MVC architecture)
- **MyBatis** (SQL Mapper Framework)
- **Thymeleaf** (Server-side rendering)
- **MySQL** (Relational Database)
- **JUnit** (Unit testing)
- **Git + GitHub** (Team collaboration and version control)

---

## 🚀 Key Features

- **Session-based login/logout**
- **Admin dashboard and user management**
- **Inbound / Outbound stock management**
- **Micro-fulfillment system based on distance using Geo algorithm (warehouse selection logic)**
- **Inventory tracking and stock history**
- **Global exception handling and input validation**
- **Clean and maintainable MVC structure**

---

## 📚 What I Learned

Through this project, I practiced:

- Designing layered architecture (Controller → Service → Repository → Database)
- Building user login/approval flows and managing roles
- Writing SQL and integrating with MyBatis Mapper XML
- Handling global exceptions and validation
- Creating admin interfaces with Thymeleaf
- Using Git branches and Pull Requests in a team-based workflow
- Solving real backend issues (see below)

---

## 🧹 Troubleshooting and Problem Solving

I solved many backend challenges, including:

- **Session Handling:** Resolved unstable login/logout behaviors by properly managing Spring sessions.Ensured session attributes persisted across user actions, implemented secure logout using session invalidation, and added login-check filters to restrict unauthorized access. This resulted in a much smoother and more reliable login/logout experience for users.
- **MyBatis Mapping Errors:** Fixed namespace and parameter issues
- **Foreign Key Constraints:** Resolved `NullPointerException` in user registration
- **Thymeleaf Binding Errors:** Fixed attribute and form submission problems
- **Transaction Consistency:** Managed rollback and partial commit scenarios
- **Validation & Error Handling:** Added global exception handler and validation
- **Version Control:** Managed Git conflicts and cleaned up Pull Requests

---

## 🤝 Teamwork and Collaboration

Although this was a learning project, I followed real team practices:

- Used GitHub Issues, branches, and Pull Requests
- Participated in planning and peer review processes
- Aligned coding styles and resolved conflicts through feedback

---

## 📌 Areas to Improve

There are still areas for growth to make this more production-ready:

- **Entity and DTO separation** can be improved for cleaner domain structure
- **Service layer testing** should be expanded with unit/integration tests
- **REST APIs** could be added for frontend or external integration
- **Authorization** could be more fine-grained at the method level
- **CI/CD workflow** can be introduced for automated testing and deployment

---

## 🔧 How I Plan to Improve

Planned next steps for this project:

- Add REST API endpoints
- Improve test coverage
- Refactor domain layers (DTO/Entity/VO separation)
- Add method-level security
- Set up CI/CD pipeline using GitHub Actions and Docker

---

## 📬 Contact

For any inquiries, collaboration opportunities, or if you'd like to discuss this project in detail, feel free to reach out.

---
