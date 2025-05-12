package com.edu.coursemanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.coursemanagement.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment,UUID> {

    
}