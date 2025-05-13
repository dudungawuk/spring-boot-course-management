package com.edu.coursemanagement.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.edu.coursemanagement.dto.request.ProfessorRequest;
import com.edu.coursemanagement.dto.request.ProfessorUpdateRequest;
import com.edu.coursemanagement.dto.response.ProfessorResponse;
import com.edu.coursemanagement.entity.Professor;

@Mapper(componentModel="spring")
public interface ProfessorMapper {
    @Mapping(source = "department.id",target = "departmentId")
    ProfessorResponse toResponse(Professor professor);
    Professor toEntity(ProfessorRequest professorRequest);
    List<ProfessorResponse> toListResponses(List<Professor> professors);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfessorFromRequest(ProfessorUpdateRequest professorRequest, @MappingTarget Professor professor);
}
