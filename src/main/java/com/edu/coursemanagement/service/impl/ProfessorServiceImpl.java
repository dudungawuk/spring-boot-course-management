package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.edu.coursemanagement.dto.request.ProfessorRequest;
import com.edu.coursemanagement.dto.request.ProfessorUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.ProfessorResponse;
import com.edu.coursemanagement.entity.Department;
import com.edu.coursemanagement.entity.Professor;
import com.edu.coursemanagement.exception.BadRequestException;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.mapper.ProfessorMapper;
import com.edu.coursemanagement.repository.ProfessorRepository;
import com.edu.coursemanagement.service.CourseOfferingService;
import com.edu.coursemanagement.service.ProfessorService;
import com.edu.coursemanagement.util.DepartmentHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;
    private final DepartmentHelper departmentHelper;
    private final CourseOfferingService courseOfferingService;

    @Override
    public ProfessorResponse createProfessor(ProfessorRequest professorRequest) {
        Department department = departmentHelper.getDepartmentById(professorRequest.departmentId());
        Professor professor = professorMapper.toEntity(professorRequest);
        professor.setDepartment(department);
        return professorMapper.toResponse(professorRepository.save(professor));
    }

    @Override
    public void deleteProfessorById(UUID professorId) {
        Professor professor = getProfessorEntityById(professorId);
        if(!professor.getCourseTaught().isEmpty() || professor.getDepartment().getHeadOfDepartment().equals(professor)){
            throw new BadRequestException("Professor still has linked courses or is head of department!");
        }
        professorRepository.delete(professor);
    }

    @Override
    public List<ProfessorResponse> getAllProfessors(UUID departmentId) {
        if(departmentId==null){
            return professorMapper.toListResponses(professorRepository.findAll());
        }
        return getProfesorsByDepartmentId(departmentId);
    }

    @Override
    public List<CourseOfferingResponse> getCourseOfferingsTaughtByProfessor(UUID professorId) {
        return courseOfferingService.getAllCourseOfferingsByProfessorId(professorId);
    }

    @Override
    public ProfessorResponse getProfessorById(UUID professorId) {
        Professor professor = professorRepository.findById(professorId).orElseThrow(()->new ResourceNotFoundException("Professor not found"));
        return professorMapper.toResponse(professor);
    }

    @Override
    public ProfessorResponse updateProffesorById(UUID professorId, ProfessorUpdateRequest professorRequest) {
        Professor professor = getProfessorEntityById(professorId);
        professorMapper.updateProfessorFromRequest(professorRequest,professor);
        Department department = departmentHelper.getDepartmentById(professorRequest.departementId());
        professor.setDepartment(department);
        return professorMapper.toResponse(professorRepository.save(professor));
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
