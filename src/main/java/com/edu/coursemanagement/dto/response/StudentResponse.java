package com.edu.coursemanagement.dto.response;

import java.time.LocalDate;
import java.util.List;

import com.edu.coursemanagement.entity.Enrollment;

public record StudentResponse(
    String studentIdNumber,
    String firstName,
    String lastName,
    String email,
    LocalDate dateOfBirth,
    List<Enrollment> enrollments
) {

}
