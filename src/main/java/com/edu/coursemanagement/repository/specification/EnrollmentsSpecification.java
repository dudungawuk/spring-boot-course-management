package com.edu.coursemanagement.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.edu.coursemanagement.dto.request.EnrollmentFilterRequest;
import com.edu.coursemanagement.entity.Enrollment;

import jakarta.persistence.criteria.Predicate;

public class EnrollmentsSpecification {
    public static Specification<Enrollment> filterBy(EnrollmentFilterRequest filter) {
        return(root,query,criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(filter.courseOfferingId() != null){
                predicates.add(criteriaBuilder.equal(root.get("courseOffering").get("id"), filter.courseOfferingId()));
            }
            if(filter.studentId() != null){
                predicates.add(criteriaBuilder.equal(root.get("student").get("id"), filter.studentId()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
