package com.edu.coursemanagement.dto.request;

import java.util.UUID;

public record ProfessorRequest(
    String firstName,
    String lastName,
    String email,
    String officeNumber,
    UUID departmentId
    ) {}
