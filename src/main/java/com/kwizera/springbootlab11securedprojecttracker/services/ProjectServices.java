package com.kwizera.springbootlab11securedprojecttracker.services;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Project;

import java.util.Optional;
import java.util.UUID;

public interface ProjectServices {
    Project createProject(Project project) throws DuplicateRecordException;

    Optional<Project> findProjectById(UUID projectId);
}
