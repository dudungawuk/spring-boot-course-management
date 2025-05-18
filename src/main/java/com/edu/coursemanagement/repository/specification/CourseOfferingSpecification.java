package com.edu.coursemanagement.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.edu.coursemanagement.dto.request.CourseOfferingFilter;
import com.edu.coursemanagement.entity.CourseOffering;

import jakarta.persistence.criteria.Predicate;

public class CourseOfferingSpecification {
    
    public static Specification<CourseOffering> filterBy(CourseOfferingFilter filter) {
        return (root,query,criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(filter.courseId() != null){
                predicates.add(criteriaBuilder.equal(root.get("course").get("id"), filter.courseId()));
            }
            if(filter.professorId() != null){
                predicates.add(criteriaBuilder.equal(root.get("professor").get("id"), filter.professorId()));
            }
            if(filter.semester() != null){
                predicates.add(criteriaBuilder.equal(root.get("semester"), filter.semester()));
            }
            if(filter.year() != null){
                predicates.add(criteriaBuilder.equal(root.get("year"), filter.year()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
