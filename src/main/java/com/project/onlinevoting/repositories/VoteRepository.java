package com.project.onlinevoting.repositories;

import com.project.onlinevoting.entities.User;
import com.project.onlinevoting.entities.Vote;
import com.project.onlinevoting.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    boolean existsByUser(User user);
    long countByCandidate(Candidate candidate);
}
