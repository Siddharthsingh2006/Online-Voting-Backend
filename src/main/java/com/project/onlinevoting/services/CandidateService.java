package com.project.onlinevoting.services;

import com.project.onlinevoting.entities.Candidate;
import com.project.onlinevoting.repositories.CandidateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {

//    @Autowired(required=true)
    private CandidateRepository candidateRepository;
    
    public CandidateService(CandidateRepository candidateRepository)
    {
    	this.candidateRepository=candidateRepository;
    }

    // 🔹 Add or update candidate
    public Candidate saveCandidate(Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    // 🔹 Get all candidates
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    // 🔹 Find candidate by ID
    public Optional<Candidate> findById(Long id) {
        return candidateRepository.findById(id);
    }

    // 🔹 Delete candidate by ID
    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }
}
