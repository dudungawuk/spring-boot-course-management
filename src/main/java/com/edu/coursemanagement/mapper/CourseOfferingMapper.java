package com.edu.coursemanagement.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.entity.CourseOffering;

@Mapper(componentModel = "spring")
public interface CourseOfferingMapper {
    CourseOfferingResponse toResponse(CourseOffering courseOffering);
    List<CourseOfferingResponse> toListResponses(List<CourseOffering> courseOfferings);
}