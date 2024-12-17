# JWT Authentication Service

## Project Overview
This is a Spring Boot microservice that provides secure user authentication using JSON Web Tokens (JWT) with role-based access control.

## Prerequisites
- Docker
- Docker Compose
- Postman or cURL for API testing

## Project Structure
```
project-root/
│
├── Dockerfile
├── docker-compose.yml
└── README.md
```

## Getting Started

### 2. Build and Run with Docker
```bash
docker pull sid14010101/jwt-auth-management
docker run -p 8080:8080 sid14010101/jwt-auth-management
```

## Authentication Workflow

### 1. Create User
To create a new user, send a POST request to the create-user endpoint:

```bash
curl --location 'http://localhost:8080/users/create-user' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"sid@test",
    "password":"sid1234567",
    "email":"sid1@gmail.com",
    "roles":["ROLE_ADMIN","ROLE_USER"]
}'
```

### 2. User Login
Login with the created credentials to obtain a JWT token:

```bash
curl --location 'http://localhost:8080/auth/login' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username":"sid@test",
    "password":"sid1234567"
}'
```

### 3. Accessing Protected Endpoints
Use the obtained Bearer token to access role-specific endpoints:

#### User Endpoint (Requires ROLE_USER)
```bash
curl --location 'http://localhost:8080/demo/user' \
--header 'Authorization: Bearer YOUR_JWT_TOKEN'
```

#### Admin Endpoint (Requires ROLE_ADMIN)
```bash
curl --location 'http://localhost:8080/demo/admin' \
--header 'Authorization: Bearer YOUR_JWT_TOKEN'
```

## Roles
- `ROLE_USER`: Standard user access
- `ROLE_ADMIN`: Administrative access with extended privileges

## Security Features
- JWT-based authentication
- Role-based access control
- Secure password handling
- Token-based session management

## Configuration
Modify `application.properties` to customize:
- Database connection
- JWT secret
- Token expiration time

## Sample Dockerfile
```dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

## Contact
Your Name - sidaroraworking@gmail.com
