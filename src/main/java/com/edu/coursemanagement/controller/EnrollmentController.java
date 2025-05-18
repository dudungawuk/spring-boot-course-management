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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.coursemanagement.dto.request.EnrollmentRequest;
import com.edu.coursemanagement.dto.request.EnrollmentUpdateRequest;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.dto.response.StudentResponse;
import com.edu.coursemanagement.payloads.ApiResponse;
import com.edu.coursemanagement.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<EnrollmentResponse>> createEnrollments(EnrollmentRequest enrollmentRequest) {
        EnrollmentResponse enrollmentResponse = enrollmentService.createEnrollment(enrollmentRequest);
        ApiResponse<EnrollmentResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", enrollmentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{enrollmentId}")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> getEnrollmentById(@PathVariable UUID enrollmentId) {
        EnrollmentResponse enrollmentResponses = enrollmentService.getEnrollmentById(enrollmentId);
        ApiResponse<EnrollmentResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", enrollmentResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{enrollmentId}")
    public ResponseEntity<ApiResponse<EnrollmentResponse>> updateEnrollmentById(@PathVariable UUID enrollmentId, EnrollmentUpdateRequest enrollmentRequest) {
        EnrollmentResponse enrollmentResponses = enrollmentService.updateEnrollmentById(enrollmentId, enrollmentRequest);
        ApiResponse<EnrollmentResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", enrollmentResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<Void>> deleteEnrollmentById(@PathVariable UUID enrollmentId) {
        enrollmentService.deleteEnrollmentById(enrollmentId);
        ApiResponse<Void> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // @GetMapping
    // public ResponseEntity<ApiResponse<List<EnrollmentResponse>>> getAllEnrollments() {
    //     List<EnrollmentResponse> enrollmentResponses = enrollmentService.getAllEnrollments();
    //     ApiResponse<List<EnrollmentResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", enrollmentResponses);
    //     return ResponseEntity.status(HttpStatus.OK).body(response);
    // }

    // @GetMapping("{EnrollmentId}/students")
    // public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudentByEnrollmentId(@PathVariable UUID enrollmentId) {
    //     List<StudentResponse> studentResponses = enrollmentService.getAllEnrollmentByStudentId(enrollmentId);
    //     ApiResponse<List<StudentResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", studentResponses);
    //     return ResponseEntity.status(HttpStatus.OK).body(response);
    // }
}
