package com.edu.coursemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.edu.coursemanagement.entity.CourseOffering;

public interface CourseOfferingRepository
        extends JpaRepository<CourseOffering, UUID>, JpaSpecificationExecutor<CourseOffering> {
    @Query("SELECT c FROM CourseOffering c WHERE c.professor.id = :professorId")
    List<CourseOffering> findAllByProfessorId(UUID professorId);
    

}