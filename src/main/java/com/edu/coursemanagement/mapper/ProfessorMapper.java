package com.edu.coursemanagement.mapper;

import java.util.List;
import java.util.UUID;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.edu.coursemanagement.dto.request.ProfessorRequest;
import com.edu.coursemanagement.dto.request.ProfessorUpdateRequest;
import com.edu.coursemanagement.dto.response.ProfessorResponse;
import com.edu.coursemanagement.entity.Department;
import com.edu.coursemanagement.entity.Professor;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    @Mapping(source = "department",target = "departmentId",qualifiedByName = "departmentToDepartmentId")
    ProfessorResponse toResponse(Professor professor);

    @Named("departmentToDepartmentId")
    default UUID departmentToDepartmentId(Department department){
        return department.getId();
    }

    Professor toEntity(ProfessorRequest professorRequest);

    List<ProfessorResponse> toListResponses(List<Professor> professors);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProfessorFromRequest(ProfessorUpdateRequest professorRequest, @MappingTarget Professor professor);
}
