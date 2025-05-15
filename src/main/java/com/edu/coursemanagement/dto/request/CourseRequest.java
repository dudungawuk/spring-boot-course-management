package com.edu.coursemanagement.dto.request;

import java.util.UUID;

public record CourseRequest(
    String courseCode,
    String title,
    int credits,
    UUID departmentId
    ) {}
