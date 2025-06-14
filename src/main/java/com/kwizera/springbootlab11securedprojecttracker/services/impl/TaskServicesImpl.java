package com.kwizera.springbootlab11securedprojecttracker.services.impl;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.Exceptions.EntityNotFoundException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Project;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Task;
import com.kwizera.springbootlab11securedprojecttracker.repositories.TaskRepository;
import com.kwizera.springbootlab11securedprojecttracker.services.ProjectServices;
import com.kwizera.springbootlab11securedprojecttracker.services.TaskServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskServicesImpl implements TaskServices {
    private final TaskRepository taskRepository;
    private final ProjectServices projectServices;

    @Override
    public Task createTask(UUID projectId, Task task) throws DuplicateRecordException, EntityNotFoundException {
        Optional<Task> taskFound = taskRepository.findByTitleIgnoreCaseAndProjectId(task.getTitle(), projectId);

        if (taskFound.isPresent())
            throw new DuplicateRecordException("A task with the same name in the same project already exists");

        Optional<Project> projectFound = projectServices.findProjectById(projectId);

        if (projectFound.isPresent()) {
            Project project = projectFound.get();
            task.setProject(project);
            return taskRepository.save(task);
        } else {
            throw new EntityNotFoundException("Project not found");
        }
    }
}
