package com.kwizera.springbootlab11securedprojecttracker.services.impl;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Project;
import com.kwizera.springbootlab11securedprojecttracker.repositories.ProjectRepository;
import com.kwizera.springbootlab11securedprojecttracker.services.ProjectServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProjectServicesImpl implements ProjectServices {
    private final ProjectRepository projectRepository;


    @Override
    public Project createProject(Project project) throws DuplicateRecordException {
        Optional<Project> projectFound = projectRepository.findByNameIgnoreCase(project.getName());

        if (projectFound.isPresent())
            throw new DuplicateRecordException("Project with name " + project.getName() + " already exists");

        return projectRepository.save(project);
    }

    @Override
    public Optional<Project> findProjectById(UUID projectId) {
        return projectRepository.findById(projectId);
    }
}
