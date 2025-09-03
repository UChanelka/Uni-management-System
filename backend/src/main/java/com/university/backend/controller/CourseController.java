package com.university.backend.controller;

import com.university.backend.model.Course;
import com.university.backend.repository.CourseRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository repo;

    public CourseController(CourseRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Course> all() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course c) {
        return new ResponseEntity<>(repo.save(c), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Course one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Course update(@PathVariable Long id, @RequestBody Course payload) {
        var c = repo.findById(id).orElseThrow();
        c.setTitle(payload.getTitle());
        c.setCode(payload.getCode());
        return repo.save(c);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

