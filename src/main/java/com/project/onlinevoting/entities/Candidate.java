package com.project.onlinevoting.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String party;

    @Column(length = 500)
    private String description; // ✅ Add this field

    // ✅ Constructors
    public Candidate() {}

    public Candidate(String name, String party, String description) {
        this.name = name;
        this.party = party;
        this.description = description;
    }

    // ✅ Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
