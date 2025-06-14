package com.kwizera.springbootlab11securedprojecttracker.repositories;

import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
