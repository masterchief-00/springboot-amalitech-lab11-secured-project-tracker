package com.kwizera.springbootlab11securedprojecttracker.services.impl;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.Exceptions.EntityNotFoundException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Project;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;
import com.kwizera.springbootlab11securedprojecttracker.domain.enums.UserRole;
import com.kwizera.springbootlab11securedprojecttracker.repositories.ProjectRepository;
import com.kwizera.springbootlab11securedprojecttracker.services.ProjectServices;
import com.kwizera.springbootlab11securedprojecttracker.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ProjectServicesImpl implements ProjectServices {
    private final ProjectRepository projectRepository;
    private final UserServices userServices;

    @Override
    public Project createProject(Project project) throws DuplicateRecordException {
        Optional<Project> projectFound = projectRepository.findByNameIgnoreCase(project.getName());

        if (projectFound.isPresent())
            throw new DuplicateRecordException("Project with name " + project.getName() + " already exists");

        return projectRepository.save(project);
    }

    @Override
    public Project updateProject(UUID projectId, Project project, Set<UUID> developerIds) throws EntityNotFoundException {
        Optional<Project> projectFound = projectRepository.findById(projectId);

        if (projectFound.isEmpty()) throw new EntityNotFoundException("Project not found");

        List<User> developers = developerIds.stream()
                .map(userServices::findUserById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(user -> user.getRole().equals(UserRole.DEVELOPER))
                .toList();

        Project updatedProject = projectFound.get();
        updatedProject.setName(project.getName());
        updatedProject.setDescription(project.getDescription());
        updatedProject.setDeadline(project.getDeadline());
        updatedProject.setDevelopers(developers);

        return projectRepository.save(updatedProject);
    }

    @Override
    public Optional<Project> findProjectById(UUID projectId) {
        return projectRepository.findById(projectId);
    }

    @Override
    public void deleteProject(UUID projectId) {
        Optional<Project> project = findProjectById(projectId);
        project.ifPresent(projectRepository::delete);
    }
}
