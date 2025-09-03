package com.university.backend.repository;

import com.university.backend.model.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result, Long> {
    boolean existsByEnrollmentId(Long enrollmentId);
}
