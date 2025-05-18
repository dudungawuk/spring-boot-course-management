package com.edu.coursemanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.edu.coursemanagement.dto.request.CourseOfferingRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.entity.Course;
import com.edu.coursemanagement.entity.CourseOffering;
import com.edu.coursemanagement.entity.Professor;
import com.edu.coursemanagement.exception.ResourceNotFoundException;

@Mapper(componentModel = "spring")
public interface CourseOfferingMapper {

    @Mapping(source = "course", target = "courseName", qualifiedByName = "courseToCourseName")
    @Mapping(source = "professor", target = "professorName", qualifiedByName = "professorToProfessorName")
    CourseOfferingResponse toResponse(CourseOffering courseOffering);

    @Named("courseToCourseName")
    default String courseToCourseName(Course course) {
        if(course == null){
            return "Unknown";
        }
        return course.getTitle();
    }

    @Named("professorToProfessorName")
    default String professorToProfessorName(Professor professor) {
        if(professor == null){
            return "Unknown";
        }
        return professor.getFirstName() + " " + professor.getLastName();
    }

    CourseOffering toEntity(CourseOfferingRequest courseOfferingRequest);
    List<CourseOfferingResponse> toListResponses(List<CourseOffering> courseOfferings);
}