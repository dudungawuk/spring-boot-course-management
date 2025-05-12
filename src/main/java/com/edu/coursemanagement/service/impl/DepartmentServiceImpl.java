package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.dto.request.DepartmentRequest;
import com.edu.coursemanagement.dto.response.DepartmentResponse;
import com.edu.coursemanagement.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{
    @Override
    public DepartmentResponse assignHeadOfDepartment(UUID departmentId, UUID professorId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteDepartment(UUID id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DepartmentResponse getDepartmentById(UUID id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DepartmentResponse updateDepartmenById(UUID id, DepartmentRequest departmentRequest) {
        // TODO Auto-generated method stub
        return null;
    }
}

