package org.example.recruitpro.controller;

import org.example.recruitpro.dto.CandidateCreateDTO;
import org.example.recruitpro.dto.CandidateUpdateDTO;
import org.example.recruitpro.entity.Candidate;
import org.example.recruitpro.response.ApiResponse;
import org.example.recruitpro.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ApiResponse.of(
                                "201 CREATED",
                                "Candidate created successfully",
                                createdCandidate
                        )
                );
    }

    @PutMapping(
            value = "/update/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<ApiResponse<Candidate>>
    updateCandidate(
            @PathVariable Long id,

            @Valid
            @ModelAttribute
            CandidateUpdateDTO request
    ) {
        Candidate updatedCandidate =
                candidateService.updateCandidate(
                        id,
                        request
                );

        return ResponseEntity.ok(
                ApiResponse.of(
                        "200 OK",
                        "Candidate updated successfully",
                        updatedCandidate
                )
        );
    }
}
