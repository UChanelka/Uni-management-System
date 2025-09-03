package com.university.backend.repository;

import com.university.backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);
}
