package com.kwizera.springbootlab11securedprojecttracker.services;

import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Skill;

import java.util.Optional;

public interface SkillServices {
    Skill findOrCreateSkill(String skill);

    Optional<Skill> findSkill(String skill);
}
