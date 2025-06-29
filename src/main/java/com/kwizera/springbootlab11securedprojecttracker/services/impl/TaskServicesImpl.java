package com.kwizera.springbootlab11securedprojecttracker.services.impl;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.Exceptions.EntityNotFoundException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Project;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Task;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;
import com.kwizera.springbootlab11securedprojecttracker.domain.enums.TaskStatus;
import com.kwizera.springbootlab11securedprojecttracker.domain.enums.UserRole;
import com.kwizera.springbootlab11securedprojecttracker.repositories.TaskRepository;
import com.kwizera.springbootlab11securedprojecttracker.services.ProjectServices;
import com.kwizera.springbootlab11securedprojecttracker.services.TaskServices;
import com.kwizera.springbootlab11securedprojecttracker.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskServicesImpl implements TaskServices {
    private final TaskRepository taskRepository;
    private final ProjectServices projectServices;
    private final UserServices userServices;

    @Override
    public List<Task> findAll(UUID userId) {
        return taskRepository.findByDeveloperId(userId);
    }

    @Override
    public Task createTask(UUID projectId, Task task) throws DuplicateRecordException, EntityNotFoundException {
        Optional<Task> taskFound = taskRepository.findByTitleIgnoreCaseAndProjectId(task.getTitle(), projectId);

        if (taskFound.isPresent())
            throw new DuplicateRecordException("A task with the same name in the same project already exists");

        Task currentTask = taskFound.get();

        Optional<Project> projectFound = projectServices.findProjectById(projectId);
        Optional<User> developerFound = userServices.findUserById(currentTask.getDeveloper().getId());

        if (projectFound.isPresent() && developerFound.isPresent()) {
            User developer = developerFound.get();
            Project project = projectFound.get();
            task.setProject(project);
            task.setDeveloper(developer);
            return taskRepository.save(task);
        } else {
            throw new EntityNotFoundException("Project not found");
        }
    }

    @Override
    public Task updateTaskStatus(UUID taskId, TaskStatus newStatus) throws EntityNotFoundException {
        Optional<Task> taskFound = taskRepository.findById(taskId);
        if (taskFound.isEmpty()) {
            throw new EntityNotFoundException("Task not found");
        }

        Task task = taskFound.get();
        task.setStatus(newStatus);

        return taskRepository.save(task);
    }

    @Override
    public Task updateTask(UUID taskId, Task task) throws EntityNotFoundException {
        Optional<Task> taskFound = taskRepository.findById(taskId);
        if (taskFound.isEmpty()) {
            throw new EntityNotFoundException("Task not found");
        }

        Optional<User> userFound = userServices.findUserById(task.getDeveloper().getId());
        if (userFound.isEmpty()) throw new EntityNotFoundException("Developer not found");
        User user = userFound.get();

        if (!user.getRole().equals(UserRole.DEVELOPER))
            throw new IllegalArgumentException("Tasks can only be assigned to developers");

        Task updatedTask = taskFound.get();
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setDeveloper(user);
        updatedTask.setStatus(task.getStatus());

        return taskRepository.save(updatedTask);
    }

    @Override
    public void deleteTask(UUID taskId) {
        Optional<Task> taskFound = taskRepository.findById(taskId);
        taskFound.ifPresent(taskRepository::delete);
    }
}
