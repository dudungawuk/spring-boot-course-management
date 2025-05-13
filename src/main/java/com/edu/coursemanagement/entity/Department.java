package com.edu.coursemanagement.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter
@RequiredArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "prof_id",unique = true,nullable = true)
    private Professor headOfDepartment;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Professor> professors;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Course> courseOffered;
}
