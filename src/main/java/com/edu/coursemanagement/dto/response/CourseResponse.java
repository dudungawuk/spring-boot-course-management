package com.edu.coursemanagement.dto.response;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.entity.Department;

public record CourseResponse(
        UUID id,
        String courseCode,
        String title,
        int credits,
        Department department,
        List<CourseOfferingResponse> offerings) {
}
