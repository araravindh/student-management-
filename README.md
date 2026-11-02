# Student Management System - Java JDBC MySQL

Menu-driven Java console project.

## Modules
- Student registration, list, search, update and delete (CRUD)
- Course creation and listing
- Course enrollment
- Attendance entry and report
- Marks entry and result generation with SQL JOIN

## Stack
Java 17, JDBC, MySQL 8+, Maven.

## Setup
1. Run `database/student_management.sql` in MySQL Workbench.
2. Open `DBConnection.java` and set your MySQL username/password.
3. Import the folder as a Maven project in IntelliJ IDEA or Eclipse.
4. Wait for Maven to download MySQL Connector/J.
5. Run `com.dinesh.sms.Main`.

## Test order
Register a student -> list/search -> list courses -> enroll -> enter attendance -> enter marks -> generate result.

Use date format `YYYY-MM-DD`. Attendance status must be `PRESENT` or `ABSENT`.

## GitHub
Never upload your real database password. Keep `your_password` as a placeholder in the public repository and configure your own local password privately.
