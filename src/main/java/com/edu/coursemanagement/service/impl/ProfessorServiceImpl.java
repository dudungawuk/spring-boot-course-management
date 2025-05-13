package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.edu.coursemanagement.dto.request.ProfessorRequest;
import com.edu.coursemanagement.dto.request.ProfessorUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.ProfessorResponse;
import com.edu.coursemanagement.entity.Professor;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.mapper.ProfessorMapper;
import com.edu.coursemanagement.repository.ProfessorRepository;
import com.edu.coursemanagement.service.ProfessorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

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
        Professor professor = professorRepository.findById(professorId).orElseThrow(()->new ResourceNotFoundException("Professor not found"));
        return professorMapper.toResponse(professor);
    }

    @Override
    public ProfessorResponse updateProffesorById(UUID professorId, ProfessorUpdateRequest professorRequest) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Professor getProfessorEntityById(UUID professorId){
        return professorRepository.findById(professorId).orElseThrow(()->new ResourceNotFoundException("Professor not found"));
    }

    @Override
    public List<ProfessorResponse> getProfesorsByDepartmentId(UUID departmentId) {
        List<Professor> professors = professorRepository.findAllByDepartmentId(departmentId);
        if(professors.isEmpty()||professors==null){
            throw new ResourceNotFoundException("Data empty or wrong department id");
        }
        return professorMapper.toListResponses(professors);
    }
}
