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
import org.springframework.web.bind.annotation.RestController;

import com.edu.coursemanagement.dto.request.DepartmentRequest;
import com.edu.coursemanagement.dto.response.DepartmentResponse;
import com.edu.coursemanagement.dto.response.ProfessorResponse;
import com.edu.coursemanagement.payloads.ApiResponse;
import com.edu.coursemanagement.service.DepartmentService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<DepartmentResponse>> createDepartment(@RequestBody DepartmentRequest departmentRequest){
        DepartmentResponse departmentResponse = departmentService.createDepartment(departmentRequest);
        ApiResponse<DepartmentResponse> response = new ApiResponse<>(HttpStatus.CREATED.value(), "CREATED", departmentResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DepartmentResponse>>> getAllDepartment (){
        List<DepartmentResponse> departmentResponses = departmentService.getAllDepartments();
        ApiResponse<List<DepartmentResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", departmentResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{departmentId}")
    public ResponseEntity<ApiResponse<DepartmentResponse>> getDepartmentById (@PathVariable UUID departmentId){
        DepartmentResponse departmentResponses = departmentService.getDepartmentById(departmentId);
        ApiResponse<DepartmentResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", departmentResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("{departmentId}/head/{professorId}")
        public ResponseEntity<ApiResponse<DepartmentResponse>> assignHeadOfDepartment (@PathVariable UUID departmentId,@PathVariable UUID professorId){
        DepartmentResponse departmentResponses = departmentService.assignHeadOfDepartment(departmentId,professorId);
        ApiResponse<DepartmentResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", departmentResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("{departmentId}/professors")
    public ResponseEntity<ApiResponse<List<ProfessorResponse>>> getAllProfessorsByDepartmentId (@PathVariable UUID departmentId){
        List<ProfessorResponse> professorResponse = departmentService.getAllProfessorsByDepartmentId(departmentId);
        ApiResponse<List<ProfessorResponse>> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", professorResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("{departmentId}")
    public ResponseEntity<ApiResponse<String>> deleteDepartmentById(@PathVariable UUID departmentId){
        departmentService.deleteDepartment(departmentId);
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.NO_CONTENT.value(), "NO CONTENT", null);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @PutMapping("{departmentId}")
        public ResponseEntity<ApiResponse<DepartmentResponse>> assignHeadOfDepartment (@PathVariable UUID departmentId,@RequestBody DepartmentRequest departmentRequest){
        DepartmentResponse departmentResponses = departmentService.updateDepartmenById(departmentId, departmentRequest);
        ApiResponse<DepartmentResponse> response = new ApiResponse<>(HttpStatus.OK.value(), "OK", departmentResponses);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
