package com.project.onlinevoting.controller;

import com.project.onlinevoting.entities.User;
import com.project.onlinevoting.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5174"}) // Your React frontend port
public class AuthController {

    @Autowired
    private UserService userService;

    // ---------------------------
    // 🔹 REGISTER USER/ADMIN API
    // ---------------------------
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        System.out.println("Register Data: " + user);

        if (user.getEmail() == null || user.getPassword() == null || user.getRole() == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "All fields are required"));
        }

        if (userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email already exists"));
        }

        userService.registerUser(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("userId", user.getId());
        response.put("role", user.getRole());

        return ResponseEntity.ok(response);
    }

    // ---------------------------
    // 🔹 LOGIN USER/ADMIN API
    // ---------------------------
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        System.out.println("Login Request: " + loginData);

        String email = loginData.get("email");
        String password = loginData.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Email and password are required"));
        }

        User user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(404).body(Map.of("message", "User not found"));
        }

        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid password"));
        }

        // ✅ Return different messages for admin or voter
        String role = user.getRole();
        String successMsg = role.equalsIgnoreCase("ADMIN")
                ? "Admin login successful"
                : "Voter login successful";

        Map<String, Object> response = new HashMap<>();
        response.put("message", successMsg);
        response.put("userId", user.getId());
        response.put("role", role);
        response.put("email", user.getEmail());

        return ResponseEntity.ok(response);
    }
}
