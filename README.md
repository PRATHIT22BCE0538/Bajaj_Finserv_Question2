# Bajaj Finserv Health Qualifier - Spring Boot Webhook Application

**Registration Number**: 22BCE0538 (Even - Question 2)

## Overview
This Spring Boot application implements the requirements for the Bajaj Finserv Health Qualifier Java assessment. The application automatically executes the complete webhook workflow on startup without requiring any controller endpoints.

## Features
- ✅ Automatic webhook generation on application startup
- ✅ SQL problem solving (Question 2 for even registration numbers)
- ✅ JWT-based authentication for solution submission
- ✅ RestTemplate for HTTP client operations
- ✅ CommandLineRunner for startup execution
- ✅ No controller endpoints required

## Project Structure
```
src/
├── main/
│   ├── java/com/bajajfinserv/webhook/
│   │   ├── WebhookSpringBootApplication.java    # Main application class
│   │   ├── WebhookService.java                  # Webhook operations service
│   │   ├── SqlQueryService.java                 # SQL solution provider
│   │   └── dto/
│   │       ├── WebhookGenerationRequest.java    # Request DTO
│   │       ├── WebhookGenerationResponse.java   # Response DTO
│   │       └── SolutionSubmissionRequest.java   # Submission DTO
│   └── resources/
│       └── application.properties               # Configuration
└── test/                                        # Test files
```

## Requirements
- Java 17 or higher
- Maven 3.6 or higher
- Internet connection for API calls

## How to Run

### Method 1: Using Maven
```bash
# Clone or download the project
cd webhook-spring-boot-app

# Build the application
mvn clean compile

# Run the application
mvn spring-boot:run
```

### Method 2: Using JAR
```bash
# Build JAR file
mvn clean package

# Run the JAR
java -jar target/webhook-spring-boot-app-1.0.0.jar
```

## Workflow
1. **Application Startup**: CommandLineRunner triggers the webhook workflow
2. **Webhook Generation**: POST request to generate webhook with registration details
3. **SQL Solution**: Generates solution for Question 2 (employee age comparison)
4. **Solution Submission**: Submits final SQL query using JWT authentication

## SQL Solution (Question 2)
The application solves the problem of counting employees younger than each employee within their respective departments:

```sql
SELECT 
    e1.EMP_ID,
    e1.FIRST_NAME,
    e1.LAST_NAME,
    d.DEPARTMENT_NAME,
    COUNT(e2.EMP_ID) as YOUNGER_EMPLOYEES_COUNT
FROM EMPLOYEE e1
JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB
GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME
ORDER BY e1.EMP_ID DESC
```

## Configuration
Update the following in `WebhookService.java` before running:
- Replace "Student Name" with actual name
- Replace "student@example.com" with actual email

## API Endpoints Used
1. **Webhook Generation**: `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
2. **Solution Submission**: `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`

## Dependencies
- Spring Boot Starter Web
- Spring Boot Starter JSON
- Jackson Databind
- Spring Boot Starter Test

## Submission Requirements
- [x] Spring Boot application with RestTemplate
- [x] CommandLineRunner for startup execution
- [x] JWT authentication implementation
- [x] No controller endpoints
- [x] Complete workflow automation

## Build Output
The JAR file will be generated at: `target/webhook-spring-boot-app-1.0.0.jar`
