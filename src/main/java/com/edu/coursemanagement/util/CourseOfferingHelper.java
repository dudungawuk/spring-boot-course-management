package com.edu.coursemanagement.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.edu.coursemanagement.entity.CourseOffering;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.repository.CourseOfferingRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CourseOfferingHelper {
    private final CourseOfferingRepository courseOfferingRepository;

    public CourseOffering getCourseOfferingEntity(UUID courseOfferingId) {
        return courseOfferingRepository.findById(courseOfferingId).orElseThrow(() -> new ResourceNotFoundException("Course Offering not found"));
    }
}
