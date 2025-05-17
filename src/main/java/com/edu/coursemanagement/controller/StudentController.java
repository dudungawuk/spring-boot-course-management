package com.edu.coursemanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.coursemanagement.dto.request.StudentRequest;
import com.edu.coursemanagement.dto.request.StudentUpdateRequest;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.dto.response.StudentResponse;
import com.edu.coursemanagement.payloads.ApiResponse;
import com.edu.coursemanagement.service.StudentService;

import lombok.RequiredArgsConstructor;

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


@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<StudentResponse>> createStudents(@RequestBody StudentRequest studentRequest) {
        StudentResponse studentResponse = studentService.createStudent(studentRequest);
        ApiResponse<StudentResponse> response = new ApiResponse<>(HttpStatus.CREATED.value(), "CREATED", studentResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentResponse>>> getAllStudents() {
        List<StudentResponse> studentResponses = studentService.getAllStudents();
        ApiResponse<List<StudentResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", studentResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{studentId}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudentById(@PathVariable UUID studentId) {
        StudentResponse studentResponse = studentService.getStudentById(studentId);
        ApiResponse<StudentResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", studentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{studentId}")
    public ResponseEntity<ApiResponse<StudentResponse>> updateStudent(@PathVariable UUID studentId, @RequestBody StudentUpdateRequest studentRequest) {
        StudentResponse studentResponse = studentService.updateStudentById(studentId, studentRequest);
        ApiResponse<StudentResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", studentResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable UUID studentId) {
        studentService.deleteStudentById(studentId);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "NO CONTENT", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("{studentId}/enrollments")
    public ResponseEntity<ApiResponse<List<EnrollmentResponse>>> getEnrollmentsByStudentId(@PathVariable UUID studentId) {
        List<EnrollmentResponse> enrollmentResponses = studentService.getAllEnrollmentsByStudentId(studentId);
        ApiResponse<List<EnrollmentResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", enrollmentResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}
