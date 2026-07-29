package org.example.recruitpro.service;

import org.example.recruitpro.dto.CandidateCreateDTO;
import org.example.recruitpro.dto.CandidateUpdateDTO;
import org.example.recruitpro.entity.Candidate;
import org.example.recruitpro.exception.ResourceNotFoundException;
import org.example.recruitpro.repository.CandidateRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(
            CandidateRepository candidateRepository
    ) {
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public Candidate createCandidate(
            CandidateCreateDTO request
    ) {
        Candidate candidate = new Candidate();

        candidate.setFullName(
                request.getFullName().trim()
        );

        candidate.setEmail(
                request.getEmail()
                        .trim()
                        .toLowerCase()
        );

        candidate.setAge(request.getAge());

        candidate.setYearsOfExperience(
                request.getYearsOfExperience()
        );

        return candidateRepository.save(candidate);
    }

    @Transactional
    public Candidate updateCandidate(
            Long id,
            CandidateUpdateDTO request
    ) {
        Candidate candidate = candidateRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate not found with id: " + id
                        )
                );

        candidate.setAddress(
                request.getAddress().trim()
        );

        candidate.setBio(
                request.getBio().trim()
        );

        return candidateRepository.save(candidate);
    }
}
