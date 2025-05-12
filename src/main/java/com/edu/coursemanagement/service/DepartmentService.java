package com.edu.coursemanagement.service;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.dto.request.DepartmentRequest;
import com.edu.coursemanagement.dto.response.DepartmentResponse;

public interface DepartmentService {
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);
    List<DepartmentResponse> getAllDepartments();
    DepartmentResponse getDepartmentById(UUID departmentId);
    DepartmentResponse updateDepartmenById(UUID departmentId,DepartmentRequest departmentRequest);
    void deleteDepartment(UUID departmentId);
    DepartmentResponse assignHeadOfDepartment(UUID departmentId,UUID professorId);
}