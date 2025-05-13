package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.edu.coursemanagement.dto.request.CourseRequest;
import com.edu.coursemanagement.dto.request.CourseUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.CourseResponse;
import com.edu.coursemanagement.entity.Course;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.mapper.CourseMapper;
import com.edu.coursemanagement.repository.CourseRepository;
import com.edu.coursemanagement.service.CourseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

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
    
    @Override
    public List<CourseResponse> getAllCoursesByDepartmentId(UUID departmentId) {
        List<Course> courses = courseRepository.findAllByDepartmentId(departmentId);
        if(courses.isEmpty()||courses==null){
            throw new ResourceNotFoundException("Data empty or wrong department id");
        }
        return courseMapper.toListResponses(courses) ;
    }
}
