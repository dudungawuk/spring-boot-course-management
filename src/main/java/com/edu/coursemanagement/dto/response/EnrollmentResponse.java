package com.edu.coursemanagement.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.edu.coursemanagement.entity.Student;

public record EnrollmentResponse(
        UUID id,
        StudentResponse student,
        CourseOfferingResponse courseOffering,
        LocalDateTime enrollmentDate,
        String grade) {
}