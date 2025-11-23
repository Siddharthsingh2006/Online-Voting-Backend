package com.project.onlinevoting.services;

import com.project.onlinevoting.entities.User;
import com.project.onlinevoting.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // -------------------------------
    // 🔹 Register new User or Admin
    // -------------------------------
    public User registerUser(User user) {
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("VOTER"); // Default role if not provided
        }
        System.out.println("Registering user: " + user.getEmail() + " | Role: " + user.getRole());
        return userRepository.save(user);
    }

    // -------------------------------
    // 🔹 Get all users
    // -------------------------------
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // -------------------------------
    // 🔹 Get all voters
    // -------------------------------
    public List<User> getAllVoters() {
        return userRepository.findByRole("VOTER");
    }

    // -------------------------------
    // 🔹 Get all admins
    // -------------------------------
    public List<User> getAllAdmins() {
        return userRepository.findByRole("ADMIN");
    }

    // -------------------------------
    // 🔹 Check if email already exists
    // -------------------------------
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // -------------------------------
    // 🔹 Find user by email
    // -------------------------------
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // -------------------------------
    // 🔹 Get user role by email
    // -------------------------------
    public String getUserRole(String email) {
        User user = userRepository.findByEmail(email);
        return (user != null) ? user.getRole() : null;
    }

    // -------------------------------
    // 🔹 Find user by ID
    // -------------------------------
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // -------------------------------
    // 🔹 Validate login credentials
    // -------------------------------
    public User validateLogin(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
