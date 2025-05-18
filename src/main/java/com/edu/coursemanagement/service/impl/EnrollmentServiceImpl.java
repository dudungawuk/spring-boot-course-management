package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.edu.coursemanagement.dto.request.EnrollmentFilterRequest;
import com.edu.coursemanagement.dto.request.EnrollmentRequest;
import com.edu.coursemanagement.dto.request.EnrollmentUpdateRequest;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.entity.Enrollment;
import com.edu.coursemanagement.mapper.EnrollmentMapper;
import com.edu.coursemanagement.repository.EnrollmentRepository;
import com.edu.coursemanagement.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    @Override
    public EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest) {
        
        return null;
    }

    @Override
    public void deleteEnrollmentById(UUID enrollmentId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments(EnrollmentFilterRequest enrollmentFilterRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EnrollmentResponse getEnrollmentById(UUID enrollmentId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EnrollmentResponse updateEnrollmentById(UUID enrollmentId, EnrollmentUpdateRequest enrollmentUpdateRequest) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public List<EnrollmentResponse> getAllEnrollmentByStudentId(UUID studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findAllByStudentId(studentId);
        return enrollmentMapper.toListResponses(enrollments);
    }
}
