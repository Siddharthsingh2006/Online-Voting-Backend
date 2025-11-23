package com.project.onlinevoting.services;

import com.project.onlinevoting.entities.Candidate;
import com.project.onlinevoting.entities.User;
import com.project.onlinevoting.entities.Vote;
import com.project.onlinevoting.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    // Cast a new vote
    public Vote castVote(User voter, Candidate candidate) {

        if (voteRepository.existsByUser(voter)) {
            throw new RuntimeException("❌ You have already voted!");
        }

        Vote vote = new Vote();
        vote.setUser(voter);
        vote.setCandidate(candidate);

        // 🔥 IMPORTANT: Save vote time
        vote.setVoteTime(LocalDateTime.now());

        return voteRepository.save(vote);
    }

    // Check if user has already voted
    public boolean hasVoted(User voter) {
        return voteRepository.existsByUser(voter);
    }

    // Get all votes
    public List<Vote> getAllVotes() {
        return voteRepository.findAll();
    }

    // Delete all votes (admin use)
    public void deleteAllVotes() {
        voteRepository.deleteAll();
    }

    // Count votes by candidate
    public long countVotesByCandidate(Candidate candidate) {
        return voteRepository.countByCandidate(candidate);
    }
}
