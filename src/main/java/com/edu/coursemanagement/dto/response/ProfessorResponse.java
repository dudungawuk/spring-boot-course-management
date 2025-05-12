package com.edu.coursemanagement.dto.response;

import java.util.UUID;

public record ProfessorResponse(
        String firstName,
        String lastName,
        String email,
        String officeNumber,
        UUID departmentId) {
}
