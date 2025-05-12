package com.edu.coursemanagement.service;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.dto.request.StudentRequest;
import com.edu.coursemanagement.dto.request.StudentUpdateRequest;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.dto.response.StudentResponse;

public interface StudentService {
    StudentResponse createStudent(StudentRequest studentRequest);
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(UUID studentId);
    StudentResponse updateStudentById(UUID studentId,StudentUpdateRequest studentUpdateRequest);
    void deleteStudentById(UUID studentId);
    List<EnrollmentResponse> getAllEnrollmentsByStudentId(UUID studentId);
} 