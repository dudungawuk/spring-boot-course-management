package com.edu.coursemanagement.dto.response;

import java.util.UUID;

public record CourseOfferingResponse(
    UUID id,
    String semester,
    Integer year,
    String location,
    Integer maxCapacity,
    String courseName,
    String professorName
) {}

