# SWAPP - Electronics Shop & Repair Management System

**SWAPP** is a robust console-based application developed in Java SE. This project represents a significant technical refresh, focusing on Clean Architecture and advanced integration with relational databases.

---

## Project Objectives

The primary goal was to rebuild a comprehensive management system using modern development standards:
* **MVC/Repository Architecture**: Clear separation of concerns for easier maintenance.
* **Data Security**: Secure authentication implemented with BCrypt hashing.
* **SQL Business Logic**: Heavy use of Stored Procedures and Triggers to ensure the integrity of financial transactions and inventory.
* **Automation**: Automatic PDF invoice generation for sales.

---

## Tech Stack

* **Language**: Java 17+
* **Database**: MySQL 8.0
* **Testing & Quality**: JUnit 5
* **Third-party Libraries**:
    * `mysql-connector-j`: JDBC Connection.
    * `jBCrypt`: Password security.
    * `iText`: PDF document generation.

---

## Key Features

### Security & Authentication
- Secure login with role management (**ADMIN**, **STAFF**).
- Passwords stored as non-reversible hashes via BCrypt.

### Sales Management (Retail Module)
- Real-time search for electronics parts and customers.
- **Transactional Process**: Order creation, invoicing, payment, and stock updates synchronized via MySQL.
- Automatic export of invoices to PDF.

### Repair Service
- Intake of electronic devices with unique tracking numbers.
- Lifecycle tracking: *Pending*, *In Progress*, *Completed*, *Delivered*.

### Dashboard & Inventory
- Stock threshold alerts (automated via SQL triggers).
- Global view of the shop's status and parts inventory.

---

## Automated Testing

The project includes a suite of unit and integration tests to ensure business logic reliability:
* **Framework**: JUnit 5.
* **Unit Tests**: Validation of security logic (`AuthServiceTest`) and financial calculations.
* **Integration Tests**: Verification of data persistence and SQL query performance (`ClientRepositoryTest`, `ArticleRepositoryTest`).
* **Coverage**: Monitored using IntelliJ IDEA Code Coverage tools to ensure code quality.

---

## Database Architecture

The `swapp_db` schema follows a normalized structure:
- **Clients & Addresses**: Split structure for better geographical flexibility.
- **Articles & Stocks**: Precise quantity tracking for devices and spare parts.
- **Transactions**: Linked `Order`, `Payment`, and `Invoice` tables for full financial traceability.

---

## Installation & Configuration

1. **Database**:
    - Import the provided `init.sql` file.
    - Configure access in `com.swapp.config.DataBaseConnector`.

2. **Dependencies**:
    - Add the `.jar` libraries (MySQL Connector, BCrypt, iText, JUnit 5) to your classpath.

3. **Execution & Testing**:
    - **App**: Run `Main.java`.
    - **Tests**: Right-click the `src/test/java` folder -> **Run 'All Tests'**.

---

## About
This project was designed as a laboratory to review Java fundamentals (JDBC, Collections, Exceptions, OOP) while exploring real-world business management challenges.