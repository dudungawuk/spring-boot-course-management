package com.edu.coursemanagement.dto.response;

import java.util.UUID;


public record CourseResponse(
        UUID id,
        String courseCode,
        String title,
        int credits,
        UUID departmentId
        // DepartmentResponse department,
        // List<CourseOfferingResponse> offerings
        ) {
}
