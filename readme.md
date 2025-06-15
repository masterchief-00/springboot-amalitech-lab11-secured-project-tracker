# LAB: Secured Project Tracker API

## Overview
A web-based project tracking application built with Spring Boot and secured with OAuth 2.0 with JWT. This application is designed to help teams organize, track, and manage their projects efficiently. It provides features for creating projects, assigning tasks, tracking progress, and managing team members.

## Features
- Google OAuth 2.0 authentication
- JWT token generation with user details
- Role-based access control (ADMIN, MANAGER, DEVELOPER, CONTRACTOR)
- RESTful API endpoints
- H2 in-memory database for development
- CORS configuration for frontend integration

## Architecture Diagram
![diagram.png](src%2Fmain%2Fresources%2Fdiagram.png)

## Entity Relationship Diagram
![project tracker.png](src%2Fmain%2Fresources%2Fproject%20tracker.png)
## Prerequisites
- Java 21 
- Maven 3.6
- Google Cloud Console account for OAuth 2.0 setup

## Google OAuth 2.0 Setup
- Go to https://console.cloud.google.com/
- Create a new project or select an existing one
- Enable the Google+ API
- Go to Credentials → Create Credentials → OAuth 2.0 Client ID
- Configure OAuth consent screen if not already done
- Set Authorized redirect URIs:
http://localhost:8080/login/oauth2/code/google (for development)

## Configuration

1. Set environment variables or update `application.yml`:
   ```bash
   export GOOGLE_CLIENT_ID=your-google-client-id
   export GOOGLE_CLIENT_SECRET=your-google-client-secret
   export JWT_SECRET=your-jwt-secret-key-at-least-32-characters
   export OAUTH2_REDIRECT_URI=http://localhost:3000/oauth2/redirect
   ```

2. Or directly in `application.yml`:
   ```yaml
   spring:
     security:
       oauth2:
         client:
           registration:
             google:
               client-id: your-google-client-id
               client-secret: your-google-client-secret

## Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

The application will start on `http://localhost:8080`

## User Roles

- **CONTRACTOR**: Default role for new users, view-only access
- **DEVELOPER**: Can be assigned to tasks
- **MANAGER**: Can create projects and assign developers
- **ADMIN**: Can manage users and access audit logs

## Database

The application uses H2 in-memory database for development. You can access the H2 console at:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: `password`

## Swagger Documentation
All endpoints are well described on the Swagger UI documentation on - [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).