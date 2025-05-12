package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.dto.request.ProfessorRequest;
import com.edu.coursemanagement.dto.request.ProfessorUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.ProfessorResponse;
import com.edu.coursemanagement.service.ProfessorService;

public class ProfessorServiceImpl implements ProfessorService {

    @Override
    public ProfessorResponse createProfessor(ProfessorRequest professorRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteProfessorById(UUID professorId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<ProfessorResponse> getAllProfessors(UUID departementId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CourseOfferingResponse> getCourseOfferingsTaughtByProfessor(UUID professorId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProfessorResponse getProfessorById(UUID professorId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProfessorResponse updateProffesorById(UUID professorId, ProfessorUpdateRequest professorRequest) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
