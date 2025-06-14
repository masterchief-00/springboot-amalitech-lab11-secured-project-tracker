package com.kwizera.springbootlab11securedprojecttracker.repositories;

import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
