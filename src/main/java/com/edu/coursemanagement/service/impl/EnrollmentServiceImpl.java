package com.edu.coursemanagement.service.impl;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.edu.coursemanagement.dto.request.EnrollmentFilterRequest;
import com.edu.coursemanagement.dto.request.EnrollmentRequest;
import com.edu.coursemanagement.dto.request.EnrollmentUpdateRequest;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.entity.CourseOffering;
import com.edu.coursemanagement.entity.Enrollment;
import com.edu.coursemanagement.entity.Student;
import com.edu.coursemanagement.exception.BadRequestException;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.mapper.EnrollmentMapper;
import com.edu.coursemanagement.repository.EnrollmentRepository;
import com.edu.coursemanagement.repository.specification.EnrollmentsSpecification;
import com.edu.coursemanagement.service.EnrollmentService;
import com.edu.coursemanagement.util.CourseOfferingHelper;
import com.edu.coursemanagement.util.StudentHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;

    private final CourseOfferingHelper courseOfferingHelper;
    private final StudentHelper studentHelper;

    @Override
    public EnrollmentResponse createEnrollment(EnrollmentRequest enrollmentRequest) {
        checkCapacity(enrollmentRequest.courseOfferingId());
        CourseOffering courseOffering = courseOfferingHelper.getCourseOfferingEntity(enrollmentRequest.courseOfferingId());
        Student student = studentHelper.getStudenEntitiy(enrollmentRequest.studentId());
        Enrollment enrollment = enrollmentMapper.toEntity(enrollmentRequest);
        enrollment.setCourseOffering(courseOffering);
        enrollment.setStudent(student);
        enrollment.setEnrollmentDate(LocalDateTime.now());

        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }

    @Override
    public void deleteEnrollmentById(UUID enrollmentId) {
        Enrollment enrollment = getEntity(enrollmentId);
        enrollmentRepository.delete(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments(EnrollmentFilterRequest enrollmentFilterRequest) {
        if(isAllFieldNull(enrollmentFilterRequest)) {
            return enrollmentMapper.toListResponses(enrollmentRepository.findAll());
        }
        Specification<Enrollment> specification = EnrollmentsSpecification.filterBy(enrollmentFilterRequest);
        List<Enrollment> enrollments = enrollmentRepository.findAll(specification);
        return enrollmentMapper.toListResponses(enrollments);
    }

    @Override
    public EnrollmentResponse getEnrollmentById(UUID enrollmentId) {
        Enrollment enrollment = getEntity(enrollmentId);
        return enrollmentMapper.toResponse(enrollment);
    }

    @Override
    public EnrollmentResponse updateEnrollmentById(UUID enrollmentId, EnrollmentUpdateRequest enrollmentUpdateRequest) {
        Enrollment enrollment = getEntity(enrollmentId);
        enrollment.setGrade(enrollmentUpdateRequest.grade());
        return enrollmentMapper.toResponse(enrollmentRepository.save(enrollment));
    }
    
    @Override
    public List<EnrollmentResponse> getAllEnrollmentByStudentId(UUID studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findAllByStudentId(studentId);
        return enrollmentMapper.toListResponses(enrollments);
    }

    private Enrollment getEntity(UUID enrollmentId) {
        return enrollmentRepository.findById(enrollmentId).orElseThrow(() -> new ResourceNotFoundException("Enrollment not found"));
    }

    private void checkCapacity(UUID courseOfferingId) {
        CourseOffering courseOffering = courseOfferingHelper.getCourseOfferingEntity(courseOfferingId);
        long count = enrollmentRepository.countByCourseOfferingId(courseOfferingId);
        if(courseOffering.getMaxCapacity() == count) {
            throw new BadRequestException ("Capacity is full");
        }
        
    }


    private boolean isAllFieldNull(EnrollmentFilterRequest filter){
        UUID studentId = filter.studentId();
        UUID courseOfferingId = filter.courseOfferingId();
        return studentId == null && courseOfferingId == null;
    }
}
