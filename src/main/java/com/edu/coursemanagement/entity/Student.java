package com.edu.coursemanagement.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@RequiredArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String studentIdNumber;

    private String firstName;

    private String  lastName;

    private String email;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    private LocalDate dateOfBirth;
}
