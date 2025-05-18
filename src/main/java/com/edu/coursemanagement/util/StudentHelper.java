package com.edu.coursemanagement.util;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.edu.coursemanagement.dto.response.StudentResponse;
import com.edu.coursemanagement.entity.Student;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.mapper.StudentMapper;
import com.edu.coursemanagement.repository.StudentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StudentHelper {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Student getStudenEntitiy(UUID studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
    }

    public List<Student> getStudentsByCourseOfferingId(UUID courseOfferingId) {
        return studentRepository.findAllByCourseOfferingId(courseOfferingId);
    }

    public List<StudentResponse> getStudentByCourseOfferingIdResponse(UUID courseOfferingId) {
        List<Student> students = getStudentsByCourseOfferingId(courseOfferingId);
        return studentMapper.toListEntities(students);
    }
}
