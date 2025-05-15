package com.edu.coursemanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.coursemanagement.dto.request.CourseRequest;
import com.edu.coursemanagement.dto.request.CourseUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.CourseResponse;
import com.edu.coursemanagement.payloads.ApiResponse;
import com.edu.coursemanagement.service.CourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(@RequestBody CourseRequest courseRequest){
        CourseResponse courseResponse = courseService.createCourse(courseRequest);
        ApiResponse<CourseResponse> response = new ApiResponse<>(HttpStatus.CREATED.value(), "CREATED", courseResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }   

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses(@RequestParam(required = false) UUID departmentId){
        List<CourseResponse> courseResponses = courseService.getAllCourses(departmentId);
        ApiResponse<List<CourseResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", courseResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{courseId}")
    public ResponseEntity<ApiResponse<String>> deleteCourse(@PathVariable UUID courseId){
        courseService.deleteCourseById(courseId);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "NO CONTENT", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("{courseId}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourseByID(@PathVariable UUID courseId){
        CourseResponse courseResponse = courseService.getCourseById(courseId);
        ApiResponse<CourseResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", courseResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{courseId}")
    public ResponseEntity<ApiResponse<CourseResponse>> editCourseById(@PathVariable UUID courseId,@RequestBody CourseUpdateRequest courseRequest){
        CourseResponse courseResponse = courseService.updateCourseById(courseId,courseRequest);
        ApiResponse<CourseResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", courseResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{courseId}/offerings")
    public ResponseEntity<ApiResponse<List<CourseOfferingResponse>>> getAllOfferingsByCourseId(@PathVariable UUID courseId){
        List<CourseOfferingResponse> courseResponses = courseService.getCourseOfferingByCourseId(courseId);
        ApiResponse<List<CourseOfferingResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", courseResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
