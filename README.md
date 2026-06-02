# Learner Management System (LMS) Backend

Spring Boot backend service for managing learners, courses, and cohorts, including cohort assignments and relationship mapping across these resources.

## Project Information

- **Project name:** Learner Management System
- **Group:** `org.airtribe`
- **Language:** Java 21
- **Build tool:** Gradle
- **Framework:** Spring Boot
- **Default server port:** `5001`
- **Database support:** MySQL (active), H2 (commented configuration available)

---

## Features

- Learner management
  - Create learner
  - Fetch learner by ID
  - List all learners
  - Filter learners by name
- Course management
  - Create course
  - Fetch course by ID
  - List all courses
- Cohort management
  - Create cohort (optionally linked to course)
  - Fetch cohort by ID
  - List all cohorts
  - Assign existing learner to cohort
  - Create learners and assign to cohort in one request
- Standardized error handling
  - 404 for missing resources
  - 409 for duplicate learner email
  - 400 for validation errors
  - 500 for unexpected failures

---

## Tech Stack

- **Java 21**
- **Spring Boot 4**
  - `spring-boot-starter-webmvc`
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-validation`
- **Persistence**
  - Spring Data JPA
  - Hibernate Validator
  - MySQL Connector/J
  - H2 (runtime option)
- **Testing**
  - JUnit Platform via Gradle `test`

---

## Design Patterns and Architectural Approach

- **Layered Architecture**
  - **Controller**: HTTP input/output handling
  - **Service**: business rules and orchestration
  - **Repository**: persistence access through `JpaRepository`
  - **Entity/DTO**: persistence and API contract separation
- **Repository Pattern**
  - Spring Data repositories abstract CRUD and query operations.
- **DTO + Mapper Pattern**
  - `ApiMapper` converts between entities and request/response DTOs to keep API models clean.
- **Global Exception Handling**
  - `@RestControllerAdvice` centralizes API error responses in a single place.

---

## Architecture

```text
Client (Postman/Web/App)
        |
        v
  [Spring MVC Controllers]
        |
        v
      [Services]
        |
        v
 [Spring Data Repositories]
        |
        v
   [MySQL / H2 Database]

Cross-cutting:
- ApiMapper: Entity <-> DTO conversion
- GlobalExceptionHandler: Unified error responses
```

### Architecture Explanation

1. The client calls REST endpoints exposed by controllers.
2. Controllers delegate business operations to services.
3. Services execute domain logic and use repositories for data operations.
4. Repositories communicate with the database via JPA.
5. Mapper classes shape entities into stable API responses.
6. Exceptions thrown from any layer are translated into consistent `ApiError` payloads.

---

## Data Model Overview

- **Learner**
  - Fields: `id`, `name`, `email`, `age`
  - Relationship: many-to-many with `Cohort`
- **Cohort**
  - Fields: `id`, `name`, `description`
  - Relationships:
    - many-to-many with `Learner`
    - many-to-one with `Course`
- **Course**
  - Fields: `id`, `name`, `description`
  - Relationship: one-to-many with `Cohort`

---

## API Reference

> Controllers support two route styles for core resources: `/api/v1/...` and legacy `/<resource>` paths.

### Utility Endpoints

#### `GET /`
- Returns hello message from service.

#### `GET /hello`
- Returns static hello message.

#### `GET /test`
- Returns a sample object payload.

---

### Learner APIs

Base paths: `/api/v1/learners` and `/learners`

#### `POST /api/v1/learners`
Create learner.

**Request**
```json
{
  "name": "Alice",
  "email": "alice@example.com",
  "age": 24
}
```

**Response**
- `200 OK` with created learner entity
- `409 Conflict` if email already exists
- `400 Bad Request` on validation failure

#### `GET /api/v1/learners/{id}`
Fetch learner by ID.

**Response**
- `200 OK` with `LearnerResponse`
- `404 Not Found` if learner does not exist

#### `GET /api/v1/learners`
List learners.

Optional query:
- `name` (exact-match filter)

Examples:
- `/api/v1/learners`
- `/api/v1/learners?name=Alice`

---

### Course APIs

Base paths: `/api/v1/courses` and `/courses`

#### `POST /api/v1/courses`
Create course.

**Request**
```json
{
  "name": "Backend Engineering",
  "description": "Spring Boot and JPA fundamentals"
}
```

**Response**
- `201 Created` with `Location` header and `CourseResponse` body

#### `GET /api/v1/courses`
List courses.

**Response**
- `200 OK` with list of `CourseResponse`

#### `GET /api/v1/courses/{id}`
Fetch course by ID.

**Response**
- `200 OK` with `CourseResponse`
- `404 Not Found` if course does not exist

---

### Cohort APIs

Base paths: `/api/v1/cohorts` and `/cohorts`

#### `POST /api/v1/cohorts`
Create cohort and optionally link to a course.

**Request**
```json
{
  "name": "Cohort Jan 2027",
  "description": "Weekend batch",
  "courseId": 1
}
```

**Response**
- `201 Created` with `Location` header and `CohortResponse`
- `404 Not Found` if provided `courseId` does not exist

#### `GET /api/v1/cohorts/{id}`
Fetch cohort by ID.

**Response**
- `200 OK` with `CohortResponse`
- `404 Not Found` if cohort does not exist

#### `GET /api/v1/cohorts`
List cohorts.

**Response**
- `200 OK` with list of `CohortResponse`

#### `POST /api/v1/cohorts/{cohortId}/learners/{learnerId}`
Assign existing learner to existing cohort.

**Response**
- `200 OK` with updated `CohortResponse`
- `404 Not Found` if cohort or learner does not exist

#### `POST /api/v1/cohorts/{cohortId}/learners`
Create learners (if email not present) and assign all to cohort.

**Request**
```json
[
  {
    "name": "Bob",
    "email": "bob@example.com",
    "age": 22
  },
  {
    "name": "Carol",
    "email": "carol@example.com",
    "age": 23
  }
]
```

**Response**
- `200 OK` with updated `CohortResponse`
- `404 Not Found` if cohort does not exist

#### Legacy endpoint (deprecated)
`POST /api/v1/cohorts/assignLearnerToCohort?cohortId={id}&learnerId={id}`

---

## Error Response Format

All handled errors return:

```json
{
  "timestamp": "2026-01-01T12:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Learner with id 99 not found",
  "path": "/api/v1/learners/99"
}
```

---

## Running the Project

### Prerequisites

- Java 21
- MySQL running locally (if using default active properties)

### Run

```bash
./gradlew bootRun
```

### Test

```bash
./gradlew test
```

> Current `application.properties` is configured for local MySQL; tests may fail if MySQL is not running and reachable with configured credentials.
