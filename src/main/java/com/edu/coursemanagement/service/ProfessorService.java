package com.edu.coursemanagement.service;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.dto.request.ProfessorRequest;
import com.edu.coursemanagement.dto.request.ProfessorUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.ProfessorResponse;
import com.edu.coursemanagement.entity.Professor;

public interface ProfessorService {
    ProfessorResponse createProfessor(ProfessorRequest professorRequest);
    List<ProfessorResponse> getAllProfessors(UUID departementId);
    ProfessorResponse getProfessorById(UUID professorId);
    ProfessorResponse updateProffesorById(UUID professorId,ProfessorUpdateRequest professorRequest);
    void deleteProfessorById(UUID professorId);
    List<CourseOfferingResponse> getCourseOfferingsTaughtByProfessor(UUID professorId);

    Professor getProfessorEntityById(UUID professorId);
    List<ProfessorResponse> getProfesorsByDepartmentId(UUID departmentId);
}
