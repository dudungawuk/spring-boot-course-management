package com.edu.coursemanagement.dto.response;

import java.time.LocalDate;
import java.util.List;


public record StudentResponse(
    String studentIdNumber,
    String firstName,
    String lastName,
    String email,
    LocalDate dateOfBirth
) {

}
