package com.project.onlinevoting.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@Entity
@Table(name = "users") // 'user' is reserved in PostgreSQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // won't be serialized to JSON
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role; // VOTER or ADMIN

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore // prevent infinite recursion in JSON
    private List<Vote> votes;

    // ----------------------------
    // Constructors
    // ----------------------------
    public User() {}

    public User(String email, String password, String name, String role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }

    // ----------------------------
    // Getters & Setters
    // ----------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public List<Vote> getVotes() { return votes; }
    public void setVotes(List<Vote> votes) { this.votes = votes; }

    // ----------------------------
    // toString
    // ----------------------------
    @Override
    public String toString() {
        return "User [id=" + id + ", email=" + email + ", name=" + name + ", role=" + role + "]";
    }
}
