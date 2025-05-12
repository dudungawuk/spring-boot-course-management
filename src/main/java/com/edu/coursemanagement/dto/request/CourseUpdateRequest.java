package com.edu.coursemanagement.dto.request;

public record CourseUpdateRequest(
    String title,
    int credits) {
}
