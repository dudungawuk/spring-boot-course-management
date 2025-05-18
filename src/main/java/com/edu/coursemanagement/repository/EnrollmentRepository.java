package com.edu.coursemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edu.coursemanagement.entity.Enrollment;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,UUID> {
    
     @Query("SELECT e FROM Enrollment e WHERE e.student.id = :studentId")
    List<Enrollment> findAllByStudentId(UUID studentId); 

    @Query("SELECT e FROM Enrollment e WHERE e.courseOffering.id = :courseOfferingId")
    List<Enrollment> findAllByCourseOfferingId(UUID courseOfferingId);
}