package com.university.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank private String title;
    @Column(unique = true, nullable = false) @NotBlank
    private String code; // e.g., CS101
}
