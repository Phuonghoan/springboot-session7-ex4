package org.example.recruitpro.repository;

import org.example.recruitpro.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository
        extends JpaRepository<Candidate, Long> {
}
