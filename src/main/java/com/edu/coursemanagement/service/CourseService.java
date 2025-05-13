package com.edu.coursemanagement.service;

import java.util.List;
import java.util.UUID;

import com.edu.coursemanagement.dto.request.CourseRequest;
import com.edu.coursemanagement.dto.request.CourseUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.CourseResponse;

public interface CourseService {
    CourseResponse createCourse(CourseRequest courseRequest);
    List<CourseResponse> getAllCourses(UUID courseId);
    CourseResponse getCourseById(UUID courseId);
    CourseResponse updateCourseById(UUID courseId,CourseUpdateRequest courseUpdateRequest);
    void deleteCourseById(UUID courseId);
    List<CourseOfferingResponse> getCourseOfferingByCourseId(UUID courseID);

    List<CourseResponse> getAllCoursesByDepartmentId(UUID departmentId);
}
