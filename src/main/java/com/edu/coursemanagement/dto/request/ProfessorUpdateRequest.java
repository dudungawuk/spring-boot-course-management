package com.edu.coursemanagement.dto.request;

import java.util.UUID;

public record ProfessorUpdateRequest(
    String email,
    String officeNumber,
    UUID departementId
    ) {}
