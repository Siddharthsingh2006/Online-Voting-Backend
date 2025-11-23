package com.project.onlinevoting.controller;

import com.project.onlinevoting.entities.Candidate;
import com.project.onlinevoting.entities.User;
import com.project.onlinevoting.entities.Vote;
import com.project.onlinevoting.dto.VoteResultDTO;
import com.project.onlinevoting.services.CandidateService;
import com.project.onlinevoting.services.UserService;
import com.project.onlinevoting.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = {"http://localhost:5173", "https://your-frontend.vercel.app"})
public class AdminController {

    @Autowired	
    private CandidateService candidateService;

    @Autowired
    private UserService userService;

    @Autowired
    private VoteService voteService;

    // 1️⃣ Add Candidate
    @PostMapping("/candidate/add")
    public ResponseEntity<Map<String, String>> addCandidate(@RequestBody Candidate candidate) {
        candidateService.saveCandidate(candidate);
        return ResponseEntity.ok(Map.of("message", "Candidate added successfully: " + candidate.getName()));
    }

    // 2️⃣ Get All Candidates
    @GetMapping("/candidates")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        return ResponseEntity.ok(candidateService.getAllCandidates());
    }

    // 3️⃣ Update Candidate
    @PutMapping("/candidate/update/{id}")
    public ResponseEntity<Map<String, String>> updateCandidate(@PathVariable Long id, @RequestBody Candidate candidateDetails) {
        Optional<Candidate> existingCandidate = candidateService.findById(id);

        if (!existingCandidate.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Candidate not found!"));
        }

        Candidate candidate = existingCandidate.get();
        candidate.setName(candidateDetails.getName());
        candidate.setParty(candidateDetails.getParty());
        candidate.setDescription(candidateDetails.getDescription());

        candidateService.saveCandidate(candidate);

        return ResponseEntity.ok(Map.of("message", "Candidate updated successfully!"));
    }

    // 4️⃣ Delete Candidate
    @DeleteMapping("/candidate/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteCandidate(@PathVariable Long id) {
        Optional<Candidate> candidate = candidateService.findById(id);

        if (!candidate.isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Candidate not found!"));
        }

        candidateService.deleteCandidate(id);

        return ResponseEntity.ok(Map.of("message", "Candidate deleted successfully!"));
    }

    // 5️⃣ All Voters
    @GetMapping("/voters")
    public ResponseEntity<List<User>> getAllVoters() {
        return ResponseEntity.ok(userService.getAllVoters());
    }

    // 6️⃣ Results (Total)
    @GetMapping("/results")
    public ResponseEntity<Map<String, Long>> getResults() {

        List<Vote> allVotes = voteService.getAllVotes();
        List<Candidate> allCandidates = candidateService.getAllCandidates();

        Map<String, Long> results = new LinkedHashMap<>();

        for (Candidate c : allCandidates) {
            results.put(c.getName(), 0L);
        }

        for (Vote v : allVotes) {
            String name = v.getCandidate().getName();
            results.put(name, results.get(name) + 1);
        }

        return ResponseEntity.ok(results);
    }

    // 7️⃣ Detailed Results (User + Candidate + Time)
    @GetMapping("/results/details")
    public ResponseEntity<List<VoteResultDTO>> getDetailedResults() {

        List<Vote> allVotes = voteService.getAllVotes();
        List<VoteResultDTO> detailedList = new ArrayList<>();

        for (Vote vote : allVotes) {
            detailedList.add(new VoteResultDTO(
                    vote.getUser().getName(),              // ✔ correct getter
                    vote.getCandidate().getName(),         // ✔ correct getter
                    vote.getVoteTime().toString()          // ✔ correct getter
            ));
        }

        return ResponseEntity.ok(detailedList);
    }

    // 8️⃣ Reset Election
    @DeleteMapping("/reset")
    public ResponseEntity<Map<String, String>> resetElectionData() {
        voteService.deleteAllVotes();
        return ResponseEntity.ok(Map.of("message", "All votes cleared. Election reset!"));
    }
}
