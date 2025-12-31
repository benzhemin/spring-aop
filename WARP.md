# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.4 learning project demonstrating Aspect-Oriented Programming (AOP) concepts. The application showcases how to use Spring AOP to implement cross-cutting concerns, specifically logging with performance metrics using the `@Around` advice pattern.

## Build and Run Commands

### Build the project
```
gradle build
```

### Run the application
The application starts on port 8081 (configured in `application.yml`):
```
gradle bootRun
```

### Run tests
```
gradle test
```

### Run a specific test
```
gradle test --tests SpringAopLearnApplicationTests
```

### Clean build artifacts
```
gradle clean
```

## Code Architecture

### Directory Structure
- **src/main/java/com/example/springaop**
  - `SpringAopLearnApplication.java` - Spring Boot application entry point
  - **controller/** - REST endpoints that expose service functionality
  - **service/** - Business logic (DataService)
  - **aspect/** - AOP aspects implementing cross-cutting concerns

### Key Components

#### LoggingAspect (src/main/java/com/example/springaop/aspect/LoggingAspect.java)
The core AOP component that intercepts method calls in DataService. It uses the `@Around` advice to:
- Log method execution start
- Measure execution time
- Handle exceptions with error logging
- Return a modified response

The pointcut `execution(* com.example.springaop.service.DataService.*(..))` targets all methods in DataService.

#### DataService (src/main/java/com/example/springaop/service/DataService.java)
Contains business logic methods that are intercepted by the LoggingAspect:
- `getData()` - Returns sample data
- `saveData(String data)` - Saves data with error simulation

#### DataController (src/main/java/com/example/springaop/controller/DataController.java)
REST endpoints for testing the AOP behavior:
- `GET /data` - Calls DataService.getData()
- `GET /save?input={value}` - Calls DataService.saveData()

### Configuration

The application runs on port 8081 and logs to both console and `logs/application.log` (see `src/main/resources/application.yml`).

### Technology Stack
- **Java 17** (via toolchain)
- **Spring Boot 3.4.0** with Spring AOP
- **Gradle** (build tool)
- **JUnit 5** (testing)
- **Lombok** (annotation processing)
- **SLF4J + Logback** (logging)

## Testing the AOP Implementation

Use curl or your browser to test the endpoints:
```
# Test getData
curl http://localhost:8081/data

# Test saveData with normal input
curl "http://localhost:8081/save?input=testdata"

# Test saveData with error input (triggers exception handling in aspect)
curl "http://localhost:8081/save?input=error"
```

Watch the console output to see the LoggingAspect logging before and after method execution, including execution time.

## Important Notes on AOP Behavior

- The `@Around` advice in LoggingAspect returns `"something else"` instead of the actual method result. This is intentional for demonstration but should be changed to return the actual result in production code.
- The aspect currently logs all method calls in DataService regardless of their success/failure status.
- Performance metrics are in milliseconds (System.currentTimeMillis()).
