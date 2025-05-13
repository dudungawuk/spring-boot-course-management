package com.edu.coursemanagement.dto.response;

import java.util.List;

public record DepartmentResponse(
        String id,
        String name,
        ProfessorResponse headOfDepartment,
        List<ProfessorResponse> professors,
        List<CourseOfferingResponse> courseOffered) {
}
