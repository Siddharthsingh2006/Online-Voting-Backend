package com.project.onlinevoting.repositories;

import com.project.onlinevoting.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity.
 * Handles CRUD operations and custom query methods for user data.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 🔹 Find user by email (used for login and validation)
    User findByEmail(String email);

    // 🔹 Optional version for safer null handling
    Optional<User> findOptionalByEmail(String email);

    // 🔹 Check if an email already exists in the system
    boolean existsByEmail(String email);

    // 🔹 Fetch all users with a given role (VOTER, ADMIN, CANDIDATE)
    List<User> findByRole(String role);

    // 🔹 (Optional) Find user by email and role – helps for separate Admin/User login
    Optional<User> findByEmailAndRole(String email, String role);
}
