package com.edu.coursemanagement.dto.request;

public record CourseOfferingUpdateRequest(
    String location,
    int maxCapacity
) {
} 
