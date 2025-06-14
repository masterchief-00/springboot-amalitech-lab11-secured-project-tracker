package com.kwizera.springbootlab11securedprojecttracker.repositories;

import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {
}
