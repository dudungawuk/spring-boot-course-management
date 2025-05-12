package com.edu.coursemanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.coursemanagement.entity.CourseOffering;

public interface CourseOfferingRepository extends JpaRepository<CourseOffering,UUID> {

    
} 