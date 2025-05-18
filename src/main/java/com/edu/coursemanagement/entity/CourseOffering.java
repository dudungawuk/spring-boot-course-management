package com.edu.coursemanagement.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "course_offerings")
@Getter
@Setter
@RequiredArgsConstructor
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name= "professor_id")
    private Professor professor;

    private String semester;

    private Integer year;

    private String location;

    private Integer maxCapacity;

    @OneToMany(mappedBy = "courseOffering")
    private List<Enrollment> enrollments;
}
