package com.edu.coursemanagement.dto.request;

import java.util.UUID;

public record CourseOfferingRequest(
    UUID courseId,
    UUID proffesorId,
    String semester,
    int year,
    String location,
    int maxCapacity
) {
} 
