package org.example.recruitpro.controller;

import org.example.recruitpro.dto.CandidateCreateDTO;
import org.example.recruitpro.entity.Candidate;
import org.example.recruitpro.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(
            CandidateService candidateService
    ) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(
            @Valid
            @RequestBody CandidateCreateDTO request
    ) {
        Candidate createdCandidate =
                candidateService.createCandidate(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCandidate);
    }
}
