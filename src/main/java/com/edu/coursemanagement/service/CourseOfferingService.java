package com.edu.coursemanagement.service;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.dto.request.CourseOfferingFilter;
import com.edu.coursemanagement.dto.request.CourseOfferingRequest;
import com.edu.coursemanagement.dto.request.CourseOfferingUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.dto.response.StudentResponse;

public interface CourseOfferingService {
    CourseOfferingResponse createCourseOffering(CourseOfferingRequest courseOfferingRequest);
    List<CourseOfferingResponse> getAllCourseOfferings(CourseOfferingFilter courseOfferingFilter);
    CourseOfferingResponse getCourseOfferingById(UUID courseOfferingId);
    CourseOfferingResponse updateCourseOfferingById(UUID courseOfferingId,CourseOfferingUpdateRequest courseOfferingUpdateRequest);
    void deleteCourseOfferingById(UUID courseOfferingId);
    // List<StudentResponse> getAllStudentsByCourseOfferingId(UUID courseOfferingId);
    List<EnrollmentResponse> getAllEnrollmentsByCourseOfferingID(UUID courseOfferingId);

    List<CourseOfferingResponse> getAllCourseOfferingsByProfessorId(UUID professorId);
}
