package com.kwizera.springbootlab11securedprojecttracker.services;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.Exceptions.EntityNotFoundException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Project;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProjectServices {
    Project createProject(Project project, Set<UUID> developerIds) throws DuplicateRecordException;

    Project updateProject(UUID projectId, Project project, Set<UUID> developerIds) throws EntityNotFoundException;

    Optional<Project> findProjectById(UUID projectId);

    void deleteProject(UUID projectId);
}
