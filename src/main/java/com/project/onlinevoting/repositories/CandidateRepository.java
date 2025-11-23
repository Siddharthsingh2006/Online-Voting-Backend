package com.project.onlinevoting.repositories;

import com.project.onlinevoting.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    // Optional: add custom queries if needed
}
