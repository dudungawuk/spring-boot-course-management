package com.edu.coursemanagement.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.edu.coursemanagement.entity.Department;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DepartmentHelper {
    private final DepartmentRepository departmentRepository;

    public Department getDepartmentById(UUID departmentId){
        return departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found!"));
    }
}
