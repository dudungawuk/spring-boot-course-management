package com.edu.coursemanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.edu.coursemanagement.dto.request.StudentRequest;
import com.edu.coursemanagement.dto.response.StudentResponse;
import com.edu.coursemanagement.entity.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toEntity(StudentRequest studentRequest);
    StudentResponse toResponse(Student student);
    List<StudentResponse> toListEntities(List<Student> students);
}
