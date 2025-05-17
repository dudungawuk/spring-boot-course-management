package com.edu.coursemanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.entity.Enrollment;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    List<EnrollmentResponse> toListResponses(List<Enrollment> enrollments);
}
