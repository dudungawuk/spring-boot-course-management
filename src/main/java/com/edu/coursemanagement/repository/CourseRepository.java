package com.edu.coursemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.coursemanagement.entity.Course;

public interface CourseRepository extends JpaRepository<Course,UUID> {
    @Query("SELECT c FROM Course c WHERE c.department.id = :departmentId")
    List<Course> findAllByDepartmentId(UUID departmentId);
} 
