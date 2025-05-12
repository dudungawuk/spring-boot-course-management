package com.edu.coursemanagement.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.edu.coursemanagement.entity.CourseOffering;
import com.edu.coursemanagement.entity.Student;

public record EnrollmentResponse(
        UUID id,
        Student student,
        CourseOffering courseOffering,
        LocalDateTime enrollmentDate,
        String grade) {
}