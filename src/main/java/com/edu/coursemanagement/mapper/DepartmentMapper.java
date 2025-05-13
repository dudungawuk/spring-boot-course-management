package com.edu.coursemanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.edu.coursemanagement.dto.request.DepartmentRequest;
import com.edu.coursemanagement.dto.response.DepartmentResponse;
import com.edu.coursemanagement.entity.Department;

@Mapper(componentModel = "spring",uses = ProfessorMapper.class)
public interface DepartmentMapper {
    DepartmentResponse toResponse(Department department);
    Department toEntity(DepartmentRequest departmentRequest);
    List<DepartmentResponse> toListResponses(List<Department> departments);
} 