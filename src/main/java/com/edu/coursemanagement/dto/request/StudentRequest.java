package com.edu.coursemanagement.dto.request;

import java.time.LocalDate;

public record StudentRequest(
        String studentIdNumber,
        String firstName,
        String lastName,
        String email,
        LocalDate dayOfBirth) {
}