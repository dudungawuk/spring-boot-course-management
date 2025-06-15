package com.edu.coursemanagement.dto.request;

import java.util.UUID;

public record EnrollmentFilter(
    UUID studentId,
    UUID courseOfferingId
) {
} 
