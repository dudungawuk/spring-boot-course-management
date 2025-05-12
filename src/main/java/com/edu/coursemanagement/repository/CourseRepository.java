package com.edu.coursemanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.coursemanagement.entity.Course;

public interface CourseRepository extends JpaRepository<Course,UUID> {

    
} 
