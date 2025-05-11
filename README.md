# University Course Management System API

## Overview

This document outlines the API for the University Course Management System. The system manages departments, professors, courses, students, course offerings, and enrollments. It allows for creating, reading, updating, and deleting (CRUD) these entities, as well as managing their relationships.

## Core Entities

1. **Department**: Represents an academic department (e.g., Computer Science).
2. **Professor**: Represents an instructor. Belongs to one Department, can teach multiple Course Offerings, and one can be a Head of Department (HOD).
3. **Course**: Represents an academic course blueprint (e.g., CS101 - Intro to Programming). Belongs to one Department.
4. **Student**: Represents a student.
5. **CourseOffering**: Represents a specific instance of a Course being taught in a semester by a Professor.
6. **Enrollment**: Represents a Student's enrollment in a specific CourseOffering, including their grade.

---

## API Endpoints

All request and response bodies are in JSON format.
Standard HTTP status codes are used (200 OK, 201 Created, 204 No Content, 400 Bad Request, 404 Not Found, 500 Internal Server Error).

---

### 1. Departments

Base URL: `/api/departments`

- **`POST /api/departments`**: Create a new department.
    - Request Body:
        
        ```json
        {
          "name": "Computer Science"
        }
        
        ```
        
    - Response: `201 Created` with the created department object (including its generated `id`).
        
        ```json
        {
          "id": 1,
          "name": "Computer Science",
          "headOfDepartment": null,
          "professors": [],
          "coursesOffered": []
        }
        
        ```
        
- **`GET /api/departments`**: Get a list of all departments.
    - Response: `200 OK` with an array of department objects.
- **`GET /api/departments/{departmentId}`**: Get a specific department by ID.
    - Response: `200 OK` with the department object or `404 Not Found`.
- **`PUT /api/departments/{departmentId}`**: Update an existing department.
    - Request Body:
        
        ```json
        {
          "name": "Advanced Computer Science"
        }
        
        ```
        
    - Response: `200 OK` with the updated department object or `404 Not Found`.
- **`DELETE /api/departments/{departmentId}`**: Delete a department.
    - Response: `204 No Content` or `404 Not Found`.
    - *Note: Consider implications on associated professors and courses (e.g., disallow if entities are linked, or reassign them).*
- **`PUT /api/departments/{departmentId}/head/{professorId}`**: Assign/Change Head of Department.
    - Request Body: (None, IDs in path)
    - Response: `200 OK` with the updated department object or `404 Not Found` for either ID.
        
        ```json
        // Example Response
        {
          "id": 1,
          "name": "Computer Science",
          "headOfDepartment": {
            "id": 101,
            "firstName": "Ada",
            "lastName": "Lovelace",
            // ... other professor details
          },
          // ...
        }
        
        ```
        
- **`GET /api/departments/{departmentId}/professors`**: Get all professors in a specific department.
    - Response: `200 OK` with an array of professor objects.
- **`GET /api/departments/{departmentId}/courses`**: Get all courses offered by a specific department.
    - Response: `200 OK` with an array of course objects.

---

### 2. Professors

Base URL: `/api/professors`

- **`POST /api/professors`**: Create a new professor.
    - Request Body:
        
        ```json
        {
          "firstName": "Alan",
          "lastName": "Turing",
          "email": "alan.turing@university.edu",
          "officeNumber": "B401",
          "departmentId": 1 // ID of the department they belong to
        }
        
        ```
        
    - Response: `201 Created` with the created professor object.
- **`GET /api/professors`**: Get a list of all professors.
    - Optional Query Parameter: `?departmentId={id}` to filter by department.
    - Response: `200 OK` with an array of professor objects.
- **`GET /api/professors/{professorId}`**: Get a specific professor by ID.
    - Response: `200 OK` or `404 Not Found`.
- **`PUT /api/professors/{professorId}`**: Update an existing professor.
    - Request Body (partial updates allowed):
        
        ```json
        {
          "email": "a.turing@university.ac.uk",
          "officeNumber": "C112",
          "departmentId": 1 // Can also be used to change department
        }
        
        ```
        
    - Response: `200 OK` or `404 Not Found`.
- **`DELETE /api/professors/{professorId}`**: Delete a professor.
    - Response: `204 No Content` or `404 Not Found`.
    - *Note: Consider implications if the professor is HOD or teaches active course offerings.*
- **`GET /api/professors/{professorId}/course-offerings`**: Get all course offerings taught by a specific professor.
    - Response: `200 OK` with an array of course offering objects.

---

### 3. Courses

Base URL: `/api/courses`

- **`POST /api/courses`**: Create a new course.
    - Request Body:
        
        ```json
        {
          "courseCode": "CS101",
          "title": "Introduction to Programming",
          "credits": 3,
          "departmentId": 1 // ID of the department offering this course
        }
        
        ```
        
    - Response: `201 Created` with the created course object.
- **`GET /api/courses`**: Get a list of all courses.
    - Optional Query Parameter: `?departmentId={id}` to filter by department.
    - Response: `200 OK` with an array of course objects.
- **`GET /api/courses/{courseId}`**: Get a specific course by ID.
    - Response: `200 OK` or `404 Not Found`.
- **`PUT /api/courses/{courseId}`**: Update an existing course.
    - Request Body:
        
        ```json
        {
          "title": "Foundations of Programming",
          "credits": 4
        }
        
        ```
        
    - Response: `200 OK` or `404 Not Found`.
- **`DELETE /api/courses/{courseId}`**: Delete a course.
    - Response: `204 No Content` or `404 Not Found`.
    - *Note: Consider implications if there are active course offerings for this course.*
- **`GET /api/courses/{courseId}/offerings`**: Get all offerings for a specific course.
    - Response: `200 OK` with an array of course offering objects.

---

### 4. Students

Base URL: `/api/students`

- **`POST /api/students`**: Create a new student.
    - Request Body:
        
        ```json
        {
          "studentIdNumber": "S2024001",
          "firstName": "Alice",
          "lastName": "Smith",
          "email": "alice.smith@student.university.edu",
          "dateOfBirth": "2003-05-15" // ISO Date format YYYY-MM-DD
        }
        
        ```
        
    - Response: `201 Created` with the created student object.
- **`GET /api/students`**: Get a list of all students.
    - Response: `200 OK` with an array of student objects.
- **`GET /api/students/{studentId}`**: Get a specific student by ID.
    - Response: `200 OK` or `404 Not Found`.
- **`PUT /api/students/{studentId}`**: Update an existing student.
    - Request Body:
        
        ```json
        {
          "email": "a.smith@student.university.edu"
        }
        
        ```
        
    - Response: `200 OK` or `404 Not Found`.
- **`DELETE /api/students/{studentId}`**: Delete a student.
    - Response: `204 No Content` or `404 Not Found`.
    - *Note: Consider implications for their enrollments.*
- **`GET /api/students/{studentId}/enrollments`**: Get all enrollments for a specific student.
    - Response: `200 OK` with an array of enrollment objects, likely including details of the course offering.

---

### 5. Course Offerings

Base URL: `/api/course-offerings`

- **`POST /api/course-offerings`**: Create a new course offering.
    - Request Body:
        
        ```json
        {
          "courseId": 1,
          "professorId": 101,
          "semester": "Fall 2024",
          "year": 2024,
          "location": "Room 301, Main Building",
          "maxCapacity": 50
        }
        
        ```
        
    - Response: `201 Created` with the created course offering object.
- **`GET /api/course-offerings`**: Get a list of all course offerings.
    - Optional Query Parameters: `?courseId={id}`, `?professorId={id}`, `?semester={name}`, `?year={yyyy}`.
    - Response: `200 OK` with an array of course offering objects.
- **`GET /api/course-offerings/{offeringId}`**: Get a specific course offering by ID.
    - Response: `200 OK` or `404 Not Found`.
- **`PUT /api/course-offerings/{offeringId}`**: Update an existing course offering.
    - Request Body:
        
        ```json
        {
          "location": "Online",
          "maxCapacity": 60
        }
        
        ```
        
    - Response: `200 OK` or `404 Not Found`.
- **`DELETE /api/course-offerings/{offeringId}`**: Delete a course offering.
    - Response: `204 No Content` or `404 Not Found`.
    - *Note: Consider implications for student enrollments.*
- **`GET /api/course-offerings/{offeringId}/students`**: Get all students enrolled in a specific course offering.
    - Response: `200 OK` with an array of student objects (potentially via their enrollment records).
- **`GET /api/course-offerings/{offeringId}/enrollments`**: Get all enrollment records for a specific course offering.
    - Response: `200 OK` with an array of enrollment objects.

---

### 6. Enrollments

This entity represents the link between a Student and a CourseOffering.

Base URL: `/api/enrollments`

- **`POST /api/enrollments`**: Enroll a student in a course offering.
    - Request Body:
        
        ```json
        {
          "studentId": 501,
          "courseOfferingId": 201,
          "enrollmentDate": "2024-07-20T10:00:00Z" // ISO DateTime, or server can set this
          // "grade" can be initially null or "IP" (In Progress)
        }
        
        ```
        
    - Response: `201 Created` with the created enrollment object.
    - *Note: Business logic should check `maxCapacity` of the `CourseOffering`.*
- **`GET /api/enrollments`**: Get a list of all enrollments.
    - Optional Query Parameters: `?studentId={id}`, `?courseOfferingId={id}`.
    - Response: `200 OK` with an array of enrollment objects.
- **`GET /api/enrollments/{enrollmentId}`**: Get a specific enrollment by ID.
    - Response: `200 OK` or `404 Not Found`.
- **`PUT /api/enrollments/{enrollmentId}`**: Update an enrollment (e.g., to assign a grade).
    - Request Body:
        
        ```json
        {
          "grade": "A" // Or "B+", "IP", etc.
        }
        
        ```
        
    - Response: `200 OK` with the updated enrollment object or `404 Not Found`.
- **`DELETE /api/enrollments/{enrollmentId}`**: Cancel/Withdraw an enrollment.
    - Response: `204 No Content` or `404 Not Found`.

---

### Advanced Query Examples (Conceptual - might need dedicated endpoints or complex GET parameters)

- **Find all students who received an 'A' grade in any course taught by a specific Professor:**
    - Could be: `GET /api/students?grade=A&taughtByProfessorId={profId}`
    - Or a dedicated endpoint: `GET /api/reports/students-by-grade-and-professor?grade=A&professorId={profId}`
- **List all courses a student is currently enrolled in (grade "IP"):**
    - `GET /api/students/{studentId}/courses?status=enrolled` (which would internally filter enrollments by grade "IP" and then get course details)
    - Or simply: `GET /api/students/{studentId}/enrollments?grade=IP` and the client reconstructs the course list.

---

## Data Models (Simplified examples for JSON Payloads)

**Department:**

```json
{
  "id": 1,
  "name": "Computer Science",
  "headOfDepartment": { /* Professor object or id */ },
  "professors": [ /* Array of Professor objects or ids */ ],
  "coursesOffered": [ /* Array of Course objects or ids */ ]
}

```

**Professor:**

```json
{
  "id": 101,
  "firstName": "Ada",
  "lastName": "Lovelace",
  "email": "ada.lovelace@university.edu",
  "officeNumber": "A101",
  "department": { /* Department object or id */ }
  // "coursesTaught" (CourseOfferings) usually fetched via dedicated endpoint
}

```

**Course:**

```json
{
  "id": 1,
  "courseCode": "CS101",
  "title": "Introduction to Programming",
  "credits": 3,
  "department": { /* Department object or id */ }
  // "offerings" usually fetched via dedicated endpoint
}

```

**Student:**

```json
{
  "id": 501,
  "studentIdNumber": "S2024001",
  "firstName": "Alice",
  "lastName": "Smith",
  "email": "alice.smith@student.university.edu",
  "dateOfBirth": "2003-05-15"
  // "enrollments" usually fetched via dedicated endpoint
}

```

**CourseOffering:**

```json
{
  "id": 201,
  "course": { /* Course object or id */ },
  "professor": { /* Professor object or id */ },
  "semester": "Fall 2024",
  "year": 2024,
  "location": "Room 301, Main Building",
  "maxCapacity": 50
  // "enrollments" usually fetched via dedicated endpoint
}

```

**Enrollment:**

```json
{
  "id": 701,
  "student": { /* Student object or id */ },
  "courseOffering": { /* CourseOffering object or id */ },
  "enrollmentDate": "2024-07-20T10:00:00Z",
  "grade": "A"
}

```

*Note: In responses, IDs might be sufficient for linked objects to avoid deeply nested structures, or DTOs (Data Transfer Objects) would be used to shape the response appropriately.*

---

## Further Considerations

- **Authentication & Authorization**: Not covered here, but a real system would require secure access control (e.g., JWT, OAuth2).
- **Pagination**: For `GET` endpoints returning lists, pagination (e.g., `?page=0&size=20`) should be implemented.
- **Error Handling**: Consistent error response formats.
- **Validation**: Input validation for all request bodies.
- **Data Transfer Objects (DTOs)**: Use DTOs to tailor request/response payloads, rather than exposing Hibernate entities directly.

```

This `README.md` provides a solid foundation for understanding the API structure for your University Course Management System. You can now start implementing the Hibernate entities and then, if you choose, build a RESTful service layer (like with Spring Boot and Spring Data JPA/REST) that exposes these kinds of endpoints.
```
