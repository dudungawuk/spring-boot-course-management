package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.edu.coursemanagement.dto.request.StudentRequest;
import com.edu.coursemanagement.dto.request.StudentUpdateRequest;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.dto.response.StudentResponse;
import com.edu.coursemanagement.entity.Student;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.mapper.StudentMapper;
import com.edu.coursemanagement.repository.StudentRepository;
import com.edu.coursemanagement.service.EnrollmentService;
import com.edu.coursemanagement.service.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final EnrollmentService enrollmentService;

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        Student student = studentMapper.toEntity(studentRequest);
        return studentMapper.toResponse(studentRepository.save(student));
    }

    @Override
    public void deleteStudentById(UUID studentId) {
        Student student = getStudentEntity(studentId);
        studentRepository.delete(student);
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollmentsByStudentId(UUID studentId) {
        return enrollmentService.getAllEnrollmentByStudentId(studentId);
    }


    @Override
    public List<StudentResponse> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return studentMapper.toListEntities(students);
    }

    @Override
    public StudentResponse getStudentById(UUID studentId) {
        return studentMapper.toResponse(getStudentEntity(studentId));
    }

    @Override
    public StudentResponse updateStudentById(UUID studentId, StudentUpdateRequest studentUpdateRequest) {
        Student student = getStudentEntity(studentId);
        student.setEmail(studentUpdateRequest.email());
        return studentMapper.toResponse(studentRepository.save(student));
    }

    private Student getStudentEntity(UUID studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }
    
}
