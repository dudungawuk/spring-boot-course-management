package com.edu.coursemanagement.dto.request;

import java.util.UUID;

public record CourseOfferingRequest(
    UUID courseId,
    UUID professorId,
    String semester,
    int year,
    String location,
    int maxCapacity
) {
} 
