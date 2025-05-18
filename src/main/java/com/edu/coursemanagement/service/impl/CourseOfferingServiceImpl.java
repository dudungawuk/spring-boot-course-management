package com.edu.coursemanagement.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.edu.coursemanagement.dto.request.CourseOfferingFilter;
import com.edu.coursemanagement.dto.request.CourseOfferingRequest;
import com.edu.coursemanagement.dto.request.CourseOfferingUpdateRequest;
import com.edu.coursemanagement.dto.response.CourseOfferingResponse;
import com.edu.coursemanagement.dto.response.EnrollmentResponse;
import com.edu.coursemanagement.dto.response.StudentResponse;
import com.edu.coursemanagement.entity.Course;
import com.edu.coursemanagement.entity.CourseOffering;
import com.edu.coursemanagement.entity.Professor;
import com.edu.coursemanagement.exception.ResourceNotFoundException;
import com.edu.coursemanagement.mapper.CourseOfferingMapper;
import com.edu.coursemanagement.repository.CourseOfferingRepository;
import com.edu.coursemanagement.repository.ProfessorRepository;
import com.edu.coursemanagement.repository.specification.CourseOfferingSpecification;
import com.edu.coursemanagement.service.CourseOfferingService;
import com.edu.coursemanagement.service.CourseService;
import com.edu.coursemanagement.service.ProfessorService;
import com.edu.coursemanagement.util.EnrollmentHelper;
import com.edu.coursemanagement.util.StudentHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseOfferingServiceImpl implements CourseOfferingService {

    private final CourseOfferingRepository courseOfferingRepository;
    private final CourseOfferingMapper courseOfferingMapper;

    private final CourseService courseService;
    private final ProfessorRepository professorRepository;

    private final StudentHelper studentHelper;
    private final EnrollmentHelper enrollmentHelper;
    @Override
    public CourseOfferingResponse createCourseOffering(CourseOfferingRequest courseOfferingRequest) {
        Course course = courseService.getCourseEntityById(courseOfferingRequest.courseId());
        //is it temp next refactor using helper to avoid circular dependency
        Professor professor = professorRepository.findById(courseOfferingRequest.professorId()).orElseThrow(()->  new ResourceNotFoundException("Professor not found"));
        CourseOffering courseOffering = courseOfferingMapper.toEntity(courseOfferingRequest);
        courseOffering.setCourse(course);
        courseOffering.setProfessor(professor);
        return courseOfferingMapper.toResponse(courseOfferingRepository.save(courseOffering));
    }

    @Override
    public void deleteCourseOfferingById(UUID courseOfferingId) {
        CourseOffering courseOffering = getCourseOfferingEntityById(courseOfferingId);
        courseOfferingRepository.delete(courseOffering);
    }

    @Override
    public List<CourseOfferingResponse> getAllCourseOfferings(CourseOfferingFilter courseOfferingFilter) {
        if(isAllFieldNull(courseOfferingFilter)){
            return courseOfferingMapper.toListResponses(courseOfferingRepository.findAll());
        }
        Specification<CourseOffering> specification = CourseOfferingSpecification.filterBy(courseOfferingFilter);
        List<CourseOffering> courseOfferings = courseOfferingRepository.findAll(specification);
        return courseOfferingMapper.toListResponses(courseOfferings);
    }

    @Override
    public List<EnrollmentResponse> getAllEnrollmentsByCourseOfferingID(UUID courseOfferingId) {
        return enrollmentHelper.getEnrollmentsByCourseOfferingId(courseOfferingId);
    }

    @Override
    public List<StudentResponse> getAllStudentsByCourseOfferingId(UUID courseOfferingId) {
        return studentHelper.getStudentByCourseOfferingIdResponse(courseOfferingId);
    }

    @Override
    public CourseOfferingResponse getCourseOfferingById(UUID courseOfferingId) {
        CourseOffering courseOffering = getCourseOfferingEntityById(courseOfferingId);
        return courseOfferingMapper.toResponse(courseOffering);
    }

    @Override
    public CourseOfferingResponse updateCourseOfferingById(UUID courseOfferingId,
            CourseOfferingUpdateRequest courseOfferingUpdateRequest) {
        CourseOffering courseOffering = getCourseOfferingEntityById(courseOfferingId);
        courseOffering.setLocation(courseOfferingUpdateRequest.location());
        courseOffering.setMaxCapacity(courseOfferingUpdateRequest.maxCapacity());
        return courseOfferingMapper.toResponse(courseOfferingRepository.save(courseOffering));
    }

    @Override
    public List<CourseOfferingResponse> getAllCourseOfferingsByProfessorId(UUID professorId) {
        return courseOfferingMapper.toListResponses(courseOfferingRepository.findAllByProfessorId(professorId));
    }
    
    private CourseOffering getCourseOfferingEntityById(UUID courseOfferingId) {
        return courseOfferingRepository.findById(courseOfferingId).orElseThrow(()->{
            throw new ResourceNotFoundException("Course Offering not found");
        });
    }

    private boolean isAllFieldNull(CourseOfferingFilter courseOfferingFilter) {
    UUID courseId = courseOfferingFilter.courseId();
    UUID professorId = courseOfferingFilter.professorId();
    String semester = courseOfferingFilter.semester();
    Integer year = courseOfferingFilter.year();
    return courseId == null && professorId == null && semester == null && year == null;
    }

}
