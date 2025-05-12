package com.edu.coursemanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.coursemanagement.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,UUID> {
    
}