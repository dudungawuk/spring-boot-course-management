package com.edu.coursemanagement.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.coursemanagement.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor,UUID> {

    
} 