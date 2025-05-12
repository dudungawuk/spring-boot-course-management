package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.dto.request.CourseRequest;
import com.edu.coursemanagement.dto.request.CourseUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.CourseResponse;
import com.edu.coursemanagement.service.CourseService;

public class CourseServiceImpl implements CourseService {

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteCourseById(UUID courseId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<CourseResponse> getAllCourses(UUID courseId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CourseResponse getCourseById(UUID courseId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CourseOfferingResponse> getCourseOfferingByCourseId(UUID courseID) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CourseResponse updateCourseById(UUID courseId, CourseUpdateRequest courseUpdateRequest) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
