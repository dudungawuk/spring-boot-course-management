package com.edu.coursemanagement.dto.response;

import java.util.List;
import java.util.UUID;


public record CourseResponse(
        UUID id,
        String courseCode,
        String title,
        int credits,
        DepartmentResponse department,
        List<CourseOfferingResponse> offerings) {
}
