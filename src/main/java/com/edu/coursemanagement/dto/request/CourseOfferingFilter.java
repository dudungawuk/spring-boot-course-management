package com.edu.coursemanagement.dto.request;

import java.util.UUID;

public record CourseOfferingFilter(
    UUID courseId,
    UUID professorId,
    String semester,
    Integer year) {
}
