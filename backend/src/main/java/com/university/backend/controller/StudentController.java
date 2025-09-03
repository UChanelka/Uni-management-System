package com.university.backend.controller;

import com.university.backend.model.Student;
import com.university.backend.repository.StudentRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Student> all() {
        return repo.findAll();
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student s) {
        return new ResponseEntity<>(repo.save(s), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Student one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student payload) {
        var s = repo.findById(id).orElseThrow();
        s.setName(payload.getName());
        s.setEmail(payload.getEmail());
        return repo.save(s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}

