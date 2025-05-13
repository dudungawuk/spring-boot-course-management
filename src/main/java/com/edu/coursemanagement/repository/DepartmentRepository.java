package com.edu.coursemanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.coursemanagement.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,UUID> {
    
}