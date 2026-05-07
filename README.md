# Gym Membership Management System
A RESTful backend application for managing gyms, membership plans, and members.

## Tech Stack
* **Java 21** (Eclipse Temurin)
* **Spring Boot 3.5.14**
* **Spring Data JPA**
* **H2 Database** (In-memory)
* **Lombok**
* **SpringDoc OpenAPI** (Swagger UI)
* **JUnit 5 & Mockito** (Testing)

## Key Features
1. **Gym Management**: Create and list gyms with unique names.
2. **Membership Plans**: Define plans (BASIC, PREMIUM, GROUP) with multi-currency support.
3. **Member Registration**: Automatic registration with capacity validation (max members limit).
4. **Membership Cancellation**: Change the status to “CANCELED” (release the spots in the plans).
5. **Revenue Report**: Monthly revenue report per gym, grouped by currency.

## How to Build and Run
### Prerequisites
* JDK 21
* Maven 3.9+

### Running the application
1. Clone the repository:
   ```bash
   git clone https://github.com/szymonkozyra/gym-management-system.git
   ```
2. Navigate to the project folder:
   ```bash
   cd gym-management-system
   ```
3. Build and run using Maven:
   ```bash
   mvn spring-boot:run
   ```

## API Documentation & Tools
Once the application is running, you can access the following tools for testing and database inspection:
*   **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) – Full interactive REST API documentation.
*   **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console) – Web interface for the in-memory database.
   *   **JDBC URL**: `jdbc:h2:mem:gymdb`
   *   **User**: `sa`
   *   **Password**: *(leave empty)*

## Sample cURL Queries
> **Note for Windows Users**: The samples below use standard Bash syntax (`\` for line breaks and `'` for strings). If you are using **CMD** or **PowerShell**, please use **Git Bash** (included with Git) for the best experience. Alternatively, run the commands as a single line and replace single quotes `'` with double quotes `"` (escaping inner quotes with `\"`), e.g.:
```cmd
curl.exe -X POST http://localhost:8080/api/gyms -H "Content-Type: application/json" -d "{\"name\": \"Iron Gym\", \"address\": \"Warsaw, Poland\", \"phoneNumber\": \"+48 123 456 789\"}"
```
1. Create a Gym
```bash
curl -X POST http://localhost:8080/api/gyms \
-H "Content-Type: application/json" \
-d '{"name": "Iron Gym", "address": "Warsaw, Poland", "phoneNumber": "+48 123 456 789"}'
```
2. Create a Membership Plan
```bash
curl -X POST http://localhost:8080/api/gyms/1/plans \
-H "Content-Type: application/json" \
-d '{"name": "Gold Member", "type": "PREMIUM", "price": 199.99, "currency": "PLN", "durationInMonths": 1, "maxMembers": 50}'
```
3. Register a Member
```bash
curl -X POST http://localhost:8080/api/plans/1/members \
-H "Content-Type: application/json" \
-d '{"fullName": "Adam Kowalski", "email": "adam.kowalski@example.com"}'
```
4. Get Revenue Report
```bash
curl -X GET http://localhost:8080/api/gyms/revenue-report
```