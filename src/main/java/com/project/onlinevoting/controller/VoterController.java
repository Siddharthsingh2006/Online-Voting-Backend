package com.project.onlinevoting.controller;

import com.project.onlinevoting.entities.Candidate;
import com.project.onlinevoting.entities.User;
import com.project.onlinevoting.services.CandidateService;
import com.project.onlinevoting.services.UserService;
import com.project.onlinevoting.services.VoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voter")
public class VoterController {

    @Autowired
    private UserService userService;

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private VoteService voteService;

    // Get all candidates
    @GetMapping("/candidates")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    // Cast vote
    @PostMapping("/vote/{voterId}/{candidateId}")
    public ResponseEntity<String> castVote(
            @PathVariable Long voterId,
            @PathVariable Long candidateId) {

        Optional<User> voter = userService.findById(voterId);
        Optional<Candidate> candidate = candidateService.findById(candidateId);

        if (voter.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Voter not found!");
        }

        if (candidate.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Candidate not found!");
        }

        if (voteService.hasVoted(voter.get())) {
            return ResponseEntity.badRequest().body("⚠️ You have already voted!");
        }

        voteService.castVote(voter.get(), candidate.get());
        return ResponseEntity.ok("✅ Vote cast for " + candidate.get().getName());
    }

    // Check if voter has voted
    @GetMapping("/hasVoted/{voterId}")
    public ResponseEntity<Boolean> hasVoted(@PathVariable Long voterId) {
        Optional<User> voter = userService.findById(voterId);

        if (voter.isEmpty()) {
            return ResponseEntity.badRequest().body(false);
        }

        return ResponseEntity.ok(voteService.hasVoted(voter.get()));
    }
}
