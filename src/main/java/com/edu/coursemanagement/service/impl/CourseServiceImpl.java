package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.edu.coursemanagement.dto.request.CourseRequest;
import com.edu.coursemanagement.dto.request.CourseUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.CourseResponse;
import com.edu.coursemanagement.entity.Course;
import com.edu.coursemanagement.entity.CourseOffering;
import com.edu.coursemanagement.entity.Department;
import com.edu.coursemanagement.exception.BadRequestException;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.mapper.CourseMapper;
import com.edu.coursemanagement.mapper.CourseOfferingMapper;
import com.edu.coursemanagement.repository.CourseRepository;
import com.edu.coursemanagement.service.CourseService;
import com.edu.coursemanagement.util.DepartmentHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final CourseOfferingMapper courseOfferingMapper;
    private final DepartmentHelper departmentHelper;

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {
        Department department = departmentHelper.getDepartmentById(courseRequest.departmentId());
        Course course = courseMapper.toEntity(courseRequest);
        course.setDepartment(department);
        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    public void deleteCourseById(UUID courseId) {
        Course course = getCourseEntityById(courseId);
        if(course.getDepartment()!=null||course.getCourseOffering()!=null){
            throw new BadRequestException("Course still has linked course offering or departments!");
        }
        courseRepository.delete(course);
    }

    @Override
    public List<CourseResponse> getAllCourses(UUID departmentId) {
        return courseMapper.toListResponses(getAllCourseByDepartmentId(departmentId));
    }

    @Override
    public CourseResponse getCourseById(UUID courseId) {
        return courseMapper.toResponse(getCourseEntityById(courseId));
    }

    @Override
    public List<CourseOfferingResponse> getCourseOfferingByCourseId(UUID courseID) {
        Course course = getCourseEntityById(courseID);
        List<CourseOffering> courseOffering = course.getCourseOffering();
        System.out.println(courseOffering);
        return courseOfferingMapper.toListResponses(courseOffering);
    }

    @Override
    public CourseResponse updateCourseById(UUID courseId, CourseUpdateRequest courseUpdateRequest) {
        Course course = getCourseEntityById(courseId);
        course.setTitle(courseUpdateRequest.title());
        course.setCredits(courseUpdateRequest.credits());
        return courseMapper.toResponse(courseRepository.save(course));
    }

    @Override
    public List<CourseResponse> getAllCoursesByDepartmentId(UUID departmentId) {
        List<Course> courses = courseRepository.findAllByDepartmentId(departmentId);
        if (courses.isEmpty() || courses == null) {
            throw new ResourceNotFoundException("Data empty or wrong department id");
        }
        return courseMapper.toListResponses(courses);
    }

    private List<Course> getAllCourseByDepartmentId(UUID departmentId) {
        if (departmentId == null) {
            return courseRepository.findAll();
        }
        departmentHelper.getDepartmentById(departmentId);
        List<Course> courses = courseRepository.findAllByDepartmentId(departmentId);
        if (courses == null) {
            throw new ResourceNotFoundException("Data empty");
        }
        return courses;
    }

    private Course getCourseEntityById(UUID courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found!"));
    }
}
