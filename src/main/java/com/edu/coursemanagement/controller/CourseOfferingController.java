package com.edu.coursemanagement.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
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

import com.edu.coursemanagement.dto.request.CourseOfferingFilter;
import com.edu.coursemanagement.dto.request.CourseOfferingRequest;
import com.edu.coursemanagement.dto.request.CourseOfferingUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.payloads.ApiResponse;
import com.edu.coursemanagement.service.CourseOfferingService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/course-offerings")
@RequiredArgsConstructor
public class CourseOfferingController {
    private final CourseOfferingService courseOfferingService;

    @PostMapping
    public ResponseEntity<ApiResponse<CourseOfferingResponse>> createCourseOffering(
            @RequestBody CourseOfferingRequest courseOfferingRequest) {
        CourseOfferingResponse courseOfferingResponse = courseOfferingService
                .createCourseOffering(courseOfferingRequest);
        ApiResponse<CourseOfferingResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK",
                courseOfferingResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CourseOfferingResponse>>> getAllCourseOfferings(
            @RequestParam(required = false) UUID courseId, @RequestParam(required = false) UUID professorId,
            @RequestParam(required = false) String semester, @RequestParam(required = false) Integer year) {
        CourseOfferingFilter courseOfferingFilter = new CourseOfferingFilter(courseId, professorId, semester, year);
        List<CourseOfferingResponse> courseOfferingResponses = courseOfferingService
                .getAllCourseOfferings(courseOfferingFilter);
        ApiResponse<List<CourseOfferingResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK",
                courseOfferingResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{courseOfferingId}")
    public ResponseEntity<ApiResponse<CourseOfferingResponse>> getCourseOfferingById(
            @PathVariable UUID courseOfferingId) {
        CourseOfferingResponse courseOfferingResponse = courseOfferingService.getCourseOfferingById(courseOfferingId);
        ApiResponse<CourseOfferingResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK",
                courseOfferingResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{courseOfferingId}")
    public ResponseEntity<ApiResponse<CourseOfferingResponse>> updateCourseOfferingById(
            @PathVariable UUID courseOfferingId, @RequestBody CourseOfferingUpdateRequest courseOfferingRequest) {
        CourseOfferingResponse courseOfferingResponse = courseOfferingService.updateCourseOfferingById(courseOfferingId,
                courseOfferingRequest);
        ApiResponse<CourseOfferingResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK",
                courseOfferingResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{courseOfferingId}")
    public ResponseEntity<ApiResponse<String>> deleteCourseOfferingById(@PathVariable UUID courseOfferingId) {
        courseOfferingService.deleteCourseOfferingById(courseOfferingId);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "NO CONTENT", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }
}
