package com.edu.coursemanagement.service;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.dto.request.EnrollmentFilterRequest;
import com.edu.coursemanagement.dto.request.EnrollmentRequest;
import com.edu.coursemanagement.dto.request.EnrollmentUpdateRequest;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest);
    List<EnrollmentResponse> getAllEnrollments(EnrollmentFilterRequest enrollmentFilterRequest);
    EnrollmentResponse getEnrollmentById(UUID enrollmentId);
    EnrollmentResponse updateEnrollmentById(UUID enrollmentId,EnrollmentUpdateRequest enrollmentUpdateRequest);
    void deleteEnrollmentById(UUID enrollmentId);
}