package com.edu.coursemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.coursemanagement.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment,UUID> {
    
     @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId")
    List<Enrollment> findAllByStudentId(UUID studentId);   
}