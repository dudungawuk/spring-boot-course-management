package com.edu.coursemanagement.util;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.mapper.EnrollmentMapper;
import com.edu.coursemanagement.repository.EnrollmentRepository;
import com.edu.coursemanagement.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnrollmentHelper {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    public List<EnrollmentResponse> getEnrollmentsByCourseOfferingId(UUID courseOfferingId) {
        return enrollmentMapper.toListResponses(enrollmentRepository.findAllByCourseOfferingId(courseOfferingId));
    }
}
