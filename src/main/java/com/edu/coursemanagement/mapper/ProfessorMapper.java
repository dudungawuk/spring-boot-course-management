package com.edu.coursemanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.edu.coursemanagement.dto.response.ProfessorResponse;
import com.edu.coursemanagement.entity.Professor;

@Mapper(componentModel="spring")
public interface ProfessorMapper {
    @Mapping(source = "department.id",target = "departmentId")
    ProfessorResponse toResponse(Professor professor);
    List<ProfessorResponse> toListResponses(List<Professor> professors);
}
