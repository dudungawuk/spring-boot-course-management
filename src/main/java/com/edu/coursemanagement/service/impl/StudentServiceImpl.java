package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.dto.request.StudentRequest;
import com.edu.coursemanagement.dto.request.StudentUpdateRequest;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.dto.response.StudentResponse;
import com.edu.coursemanagement.service.StudentService;

public class StudentServiceImpl implements StudentService{

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteStudentById(UUID studentId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollmentsByStudentId(UUID studentId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<StudentResponse> getAllStudents() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StudentResponse getStudentById(UUID studentId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public StudentResponse updateStudentById(UUID studentId, StudentUpdateRequest studentUpdateRequest) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
