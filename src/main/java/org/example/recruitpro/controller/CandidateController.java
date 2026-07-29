package org.example.recruitpro.controller;

import org.example.recruitpro.dto.CandidateCreateDTO;
import org.example.recruitpro.entity.Candidate;
import org.example.recruitpro.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<Candidate>>
    createCandidate(
            @Valid
            @RequestBody CandidateCreateDTO request
    ) {
        Candidate createdCandidate =
                candidateService.createCandidate(request);

        ApiResponse<Candidate> response =
                ApiResponse.of(
                        statusText(HttpStatus.CREATED),
                        "Candidate created successfully",
                        createdCandidate
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    private String statusText(HttpStatus status) {
        return status.value() + " " + status.name();
    }
}
