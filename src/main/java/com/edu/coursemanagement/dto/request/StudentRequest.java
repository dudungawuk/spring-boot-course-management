package com.edu.coursemanagement.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record StudentRequest(
        String studentIdNumber,
        String firstName,
        String lastName,
        String email,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateOfBirth) {
}