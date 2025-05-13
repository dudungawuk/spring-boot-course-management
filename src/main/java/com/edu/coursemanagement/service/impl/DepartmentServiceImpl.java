package com.edu.coursemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.edu.coursemanagement.dto.request.DepartmentRequest;
import com.edu.coursemanagement.dto.response.CourseResponse;
import com.edu.coursemanagement.dto.response.DepartmentResponse;
import com.edu.coursemanagement.dto.response.ProfessorResponse;
import com.edu.coursemanagement.entity.Department;
import com.edu.coursemanagement.entity.Professor;
import com.edu.coursemanagement.exception.BadRequestException;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.mapper.DepartmentMapper;
import com.edu.coursemanagement.repository.DepartmentRepository;
import com.edu.coursemanagement.service.CourseService;
import com.edu.coursemanagement.service.DepartmentService;
import com.edu.coursemanagement.service.ProfessorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    private final ProfessorService professorService;

    private final CourseService courseService;

    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponse assignHeadOfDepartment(UUID departmentId, UUID professorId) {
        Professor professor = professorService.getProfessorEntityById(professorId);
        Department department = getDepartmentByIdHelper(departmentId);
        department.setHeadOfDepartment(professor);
        DepartmentResponse departmentResponse = departmentMapper.toResponse(departmentRepository.save(department));
        return departmentResponse;
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        Department department = departmentMapper.toEntity(departmentRequest);
        if (department.getCourseOffered() == null) {
            department.setCourseOffered(new ArrayList<>());
        }
        if (department.getProfessors() == null) {
            department.setProfessors(new ArrayList<>());
        }
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    @Override
    public void deleteDepartment(UUID id) {
        Department department = getDepartmentByIdHelper(id);
        if(!department.getCourseOffered().isEmpty() || !department.getProfessors().isEmpty()){
            throw new BadRequestException("Department still has linked professors or courses!");
        }
        departmentRepository.delete(department);
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departmentMapper.toListResponses(departments);
    }

    @Override
    public DepartmentResponse getDepartmentById(UUID id) {
        Department department = getDepartmentByIdHelper(id);
        return departmentMapper.toResponse(department);
    }

    @Override
    public DepartmentResponse updateDepartmenById(UUID id, DepartmentRequest departmentRequest) {
        Department department = getDepartmentByIdHelper(id);
        department.setName(departmentRequest.name());
        return departmentMapper.toResponse(departmentRepository.save(department));
    }

    @Override
    public List<ProfessorResponse> getAllProfessorsByDepartmentId(UUID departmentId) {
        return professorService.getProfesorsByDepartmentId(departmentId);
    }

    @Override
    public List<CourseResponse> getCourseResponsesByDepartmentId(UUID departmentId) {
        return courseService.getAllCoursesByDepartmentId(departmentId);
    }

    private Department getDepartmentByIdHelper(UUID id){
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found!"));
    }

}
