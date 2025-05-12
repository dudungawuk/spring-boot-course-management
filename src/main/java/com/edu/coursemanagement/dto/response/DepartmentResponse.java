package com.edu.coursemanagement.dto.response;

import java.util.List;
import java.util.Optional;

import com.edu.coursemanagement.entity.CourseOffering;
import com.edu.coursemanagement.entity.Professor;

public record DepartmentResponse(
        String id,
        String name,
        Optional<Professor> headOfDepartment,
        List<Professor> professors,
        List<CourseOffering> courseOffered) {
}
