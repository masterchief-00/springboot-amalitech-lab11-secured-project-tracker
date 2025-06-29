package com.kwizera.springbootlab11securedprojecttracker.services;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.Exceptions.EntityNotFoundException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Task;
import com.kwizera.springbootlab11securedprojecttracker.domain.enums.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskServices {
    List<Task> findAll(UUID userId);

    Task createTask(UUID projectId, Task task) throws DuplicateRecordException, EntityNotFoundException;

    Task updateTaskStatus(UUID taskId, TaskStatus newStatus) throws EntityNotFoundException;

    Task updateTask(UUID taskId, Task task) throws EntityNotFoundException;

    void deleteTask(UUID taskId);
}
