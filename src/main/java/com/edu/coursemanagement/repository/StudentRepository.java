package com.edu.coursemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.coursemanagement.entity.Student;

public interface StudentRepository extends JpaRepository<Student,UUID>{
     @Query("SELECT s FROM Student s JOIN s.courseOffering e WHERE e.courseOffering.id = :courseOfferingId")
    List<Student> findAllByCourseOfferingId(UUID courseOfferingId);
    }  