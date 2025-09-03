package com.university.backend.controller;

import com.university.backend.model.Enrollment;
import com.university.backend.model.Student;
import com.university.backend.model.Course;
import com.university.backend.repository.EnrollmentRepository;
import com.university.backend.repository.StudentRepository;
import com.university.backend.repository.CourseRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentRepository enrollRepo;
    private final StudentRepository studentRepo;
    private final CourseRepository courseRepo;

    public EnrollmentController(EnrollmentRepository e, StudentRepository s, CourseRepository c) {
        this.enrollRepo = e;
        this.studentRepo = s;
        this.courseRepo = c;
    }

    @GetMapping
    public List<Enrollment> all() {
        return enrollRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Enrollment> enroll(@RequestBody Enrollment req) {
        Student student = studentRepo.findById(req.getStudent().getId()).orElseThrow();
        Course course = courseRepo.findById(req.getCourse().getId()).orElseThrow();

        if (enrollRepo.existsByStudentIdAndCourseId(student.getId(), course.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Enrollment e = new Enrollment(null, student, course);
        return new ResponseEntity<>(enrollRepo.save(e), HttpStatus.CREATED);
    }

    @GetMapping("/by-student/{studentId}")
    public List<Enrollment> byStudent(@PathVariable Long studentId) {
        return enrollRepo.findByStudentId(studentId);
    }

    @GetMapping("/by-course/{courseId}")
    public List<Enrollment> byCourse(@PathVariable Long courseId) {
        return enrollRepo.findByCourseId(courseId);
    }
}

