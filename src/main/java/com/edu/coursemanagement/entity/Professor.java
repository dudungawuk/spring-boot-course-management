package com.edu.coursemanagement.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "professors")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Professor {
    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    private String firstName;

    private String lastName;

    private String  email;
    
    private String officeNumber;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "professor",fetch = FetchType.LAZY)
    private List<CourseOffering> courseTaught;
}
