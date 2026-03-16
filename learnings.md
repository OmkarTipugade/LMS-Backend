# Project Learnings: Learner Management System

This document outlines the step-by-step learnings from the development of the Learner Management System.

## Step 1: Project Initialization & Configuration
- **Spring Boot 3/4 Setup**: Initialized the project with Java 21 and Gradle.
- **Dependency Management**: Integrated essential starters like `spring-boot-starter-webmvc` for API development and `spring-boot-starter-data-jpa` for database interaction.
- **Database Choices**: Configured support for both H2 (in-memory for development) and MySQL (production-ready).

## Step 2: Creating the First Endpoints
- **Controllers**: Learned how to create a simple `@RestController`.
- **Injection**: used `@Autowired` to inject services into controllers.
- **Routing**: Defined basic routes using `@GetMapping` (e.g., `/`, `/hello`).

## Step 3: Data Modeling with JPA
- **Entity Definition**: Created the [Learner](file:///Users/omkar/Desktop/LearnerManagementSystem/src/main/java/org/airtribe/LearnerManagementSystem/Entity/Learner.java#8-59) class to represent the core data.
- **Annotations**: Applied JPA annotations:
    - `@Entity`: Marks the class as a database table.
    - `@Id`: Specifies the primary key.
    - `@GeneratedValue`: Automates ID generation.
- **POJO Best Practices**: Implemented constructors, getters, and setters for data encapsulation.

## Step 4: Repository Layer (Spring Data JPA)
- **Interface-based Repositories**: Learned that extending `JpaRepository` provides built-in CRUD operations (`save`, [findAll](file:///Users/omkar/Desktop/LearnerManagementSystem/src/main/java/org/airtribe/LearnerManagementSystem/Service/LearnerService.java#23-26), `findById`, `delete`).
- **Derived Query Methods**: Created custom query methods like [findLearnerByName(String name)](file:///Users/omkar/Desktop/LearnerManagementSystem/src/main/java/org/airtribe/LearnerManagementSystem/Service/LearnerService.java#31-34) which Spring Data JPA implements automatically based on the method name.

## Step 5: Service Layer Orchestration
- **Business Logic Separation**: Implemented [LearnerService](file:///Users/omkar/Desktop/LearnerManagementSystem/src/main/java/org/airtribe/LearnerManagementSystem/Service/LearnerService.java#13-35) to act as an intermediary between the Controller and Repository.
- **Service Annotation**: Used `@Service` to register the class in the Spring Application Context.

## Step 6: Implementing RESTful CRUD
- **Handling Requests**:
    - `@PostMapping`: Used for creating new records (`/learners`).
    - `@RequestBody`: Deserializes JSON input into Java objects.
- **Dynamic Queries**:
    - `@PathVariable`: Extracts data from the URL path (e.g., `/learners/{id}`).
    - `@RequestParam`: Handles query parameters (e.g., `/learners?name=Omkar`) for filtering.

## Step 7: Testing & Verification
- **Test Controllers**: Used [TestController](file:///Users/omkar/Desktop/LearnerManagementSystem/src/main/java/org/airtribe/LearnerManagementSystem/Controller/TestController.java#7-14) and a dedicated [Test](file:///Users/omkar/Desktop/LearnerManagementSystem/src/main/java/org/airtribe/LearnerManagementSystem/Controller/TestController.java#7-14) class to verify object serialization/deserialization logic.
- **H2 Console**: used `spring-boot-h2console` to inspect the in-memory database state during development.
