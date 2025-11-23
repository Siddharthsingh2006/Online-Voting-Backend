package com.project.onlinevoting;

import com.project.onlinevoting.entities.User;
import com.project.onlinevoting.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OnlinevotingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlinevotingApplication.class, args);
        System.out.println("Online Voting System backend started successfully...!!");
    }

    /**
     * Seed an Admin user automatically on application startup.
     */
    @Bean
    CommandLineRunner initAdmin(UserService userService) {
        return args -> {
            String adminEmail = "admin@example.com";
            if (!userService.existsByEmail(adminEmail)) {
                User admin = new User(
                        adminEmail,
                        "admin123",          // Default password
                        "Admin User",        // Name
                        "ADMIN"              // Role
                );
                userService.registerUser(admin);
                System.out.println("✅ Default admin created: " + adminEmail + " / admin123");
            } else {
                System.out.println("✅ Admin already exists: " + adminEmail);
            }
        };
    }
}
