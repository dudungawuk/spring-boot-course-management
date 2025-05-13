package com.edu.coursemanagement.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.coursemanagement.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor,UUID> {
    @Query("SELECT p FROM Professor p WHERE p.department.id = :departmentId")
    List<Professor> findAllByDepartmentId(UUID departmentId);

} 