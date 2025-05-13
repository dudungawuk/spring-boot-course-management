package com.edu.coursemanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.edu.coursemanagement.dto.response.CourseResponse;
import com.edu.coursemanagement.entity.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    List<CourseResponse> toListResponses(List<Course> courses);
}
