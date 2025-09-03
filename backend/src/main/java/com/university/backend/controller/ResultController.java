package com.university.backend.controller;

import com.university.backend.model.Result;
import com.university.backend.repository.ResultRepository;
import com.university.backend.repository.EnrollmentRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    private final ResultRepository resultRepo;
    private final EnrollmentRepository enrollRepo;

    public ResultController(ResultRepository r, EnrollmentRepository e) {
        this.resultRepo = r;
        this.enrollRepo = e;
    }

    @GetMapping
    public List<Result> all() {
        return resultRepo.findAll();
    }

    @PostMapping
    public ResponseEntity<Result> record(@RequestBody Result req) {
        var enrollment = enrollRepo.findById(req.getEnrollment().getId()).orElseThrow();

        if (resultRepo.existsByEnrollmentId(enrollment.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Result saved = new Result(null, enrollment, req.getGrade());
        return new ResponseEntity<>(resultRepo.save(saved), HttpStatus.CREATED);
    }
}


