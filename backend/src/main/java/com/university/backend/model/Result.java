package com.university.backend.model;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"enrollment_id"}))
public class Result {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional=false) private Enrollment enrollment;
    @Enumerated(EnumType.STRING)
    private Grade grade;

    public enum Grade { A, B, C, D, F }
}
