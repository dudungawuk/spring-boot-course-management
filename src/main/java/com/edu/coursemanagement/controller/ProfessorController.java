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

import com.edu.coursemanagement.dto.request.ProfessorRequest;
import com.edu.coursemanagement.dto.request.ProfessorUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.DepartmentResponse;
import com.edu.coursemanagement.dto.response.ProfessorResponse;
import com.edu.coursemanagement.payloads.ApiResponse;
import com.edu.coursemanagement.service.ProfessorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/professors")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProfessorResponse>> createProfessor(@RequestBody ProfessorRequest professorRequest) {
        ProfessorResponse professorResponse = professorService.createProfessor(professorRequest);
        ApiResponse<ProfessorResponse> response = new ApiResponse<>(HttpStatus.CREATED.value(), "CREATED", professorResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProfessorResponse>>> getAllProfessors(@RequestParam(required = false) UUID departmentId) {
        List<ProfessorResponse> professorResponses = professorService.getAllProfessors(departmentId);
        ApiResponse<List<ProfessorResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", professorResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{professorId}")
    public ResponseEntity<ApiResponse<ProfessorResponse>> getProfessorById(@PathVariable UUID professorId) {
        ProfessorResponse professorResponse = professorService.getProfessorById(professorId);
        ApiResponse<ProfessorResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", professorResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{professorId}")
    public ResponseEntity<ApiResponse<ProfessorResponse>> updateProfessorById(@PathVariable UUID professorId, @RequestBody ProfessorUpdateRequest professorRequest) {
        ProfessorResponse professorResponse = professorService.updateProffesorById(professorId, professorRequest);
        ApiResponse<ProfessorResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", professorResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{professorId}")
    public ResponseEntity<ApiResponse<String>> deleteProfessorById(@PathVariable UUID professorId) {
        professorService.deleteProfessorById(professorId);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "NO CONTENT", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("{professorId}/department")
    public ResponseEntity<ApiResponse<List<CourseOfferingResponse>>> getProfessorDepartment(@PathVariable UUID professorId) {
        List<CourseOfferingResponse> courseOfferingResponses = professorService.getCourseOfferingsTaughtByProfessor(professorId);
        ApiResponse<List<CourseOfferingResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", courseOfferingResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
