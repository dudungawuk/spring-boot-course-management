package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.edu.coursemanagement.dto.request.CourseOfferingFilter;
import com.edu.coursemanagement.dto.request.CourseOfferingRequest;
import com.edu.coursemanagement.dto.request.CourseOfferingUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.dto.response.StudentResponse;
import com.edu.coursemanagement.mapper.CourseOfferingMapper;
import com.edu.coursemanagement.repository.CourseOfferingRepository;
import com.edu.coursemanagement.service.CourseOfferingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseOfferingServiceImpl implements CourseOfferingService {

    private final CourseOfferingRepository courseOfferingRepository;
    private final CourseOfferingMapper courseOfferingMapper;
    @Override
    public CourseOfferingResponse createCourseOffering(CourseOfferingRequest courseOfferingRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCourseOfferingById(UUID courseOfferingId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<CourseOfferingResponse> getAllCourseOfferings(CourseOfferingFilter courseOfferingFilter) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollmentsByCourseOfferingID(UUID courseOfferingId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<StudentResponse> getAllStudentsByCourseOfferingId(UUID courseOfferingId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CourseOfferingResponse getCourseOfferingById(UUID courseOfferingId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CourseOfferingResponse updateCourseOfferingById(UUID courseOfferingId,
            CourseOfferingUpdateRequest courseOfferingUpdateRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CourseOfferingResponse> getAllCourseOfferingsByProfessorId(UUID professorId) {
        return courseOfferingMapper.toListResponses(courseOfferingRepository.findAllByProfessorId(professorId));
    }
    
}
