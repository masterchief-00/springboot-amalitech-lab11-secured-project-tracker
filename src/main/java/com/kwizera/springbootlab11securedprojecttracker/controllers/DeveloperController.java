package com.kwizera.springbootlab11securedprojecttracker.controllers;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.EntityNotFoundException;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.TaskDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Task;
import com.kwizera.springbootlab11securedprojecttracker.domain.enums.TaskStatus;
import com.kwizera.springbootlab11securedprojecttracker.domain.mappers.EntityToDTO;
import com.kwizera.springbootlab11securedprojecttracker.services.TaskServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/developer")
@Tag(name = "Developer Controller", description = "This controller exposes all endpoints involved in developer operations")
public class DeveloperController {
    private final TaskServices taskServices;

    @PreAuthorize("hasRole('DEVELOPER')")
    @GetMapping("/{userId}/tasks")
    @Operation(summary = "Get all tasks assigned to a developer")
    public ResponseEntity<List<TaskDTO>> getTasks(@PathVariable UUID userId) {
        List<Task> allTasks = taskServices.findAll(userId);
        return new ResponseEntity<>(allTasks.stream().map(EntityToDTO::taskEntityToDTO).toList(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('DEVELOPER','MANAGER')")
    @PutMapping("/task/{taskId}")
    @Operation(summary = "Updating task status")
    public ResponseEntity<TaskDTO> updateTaskStatus(@PathVariable UUID taskId, @RequestBody TaskStatus newStatus) throws EntityNotFoundException {
        Task updatedTask = taskServices.updateTaskStatus(taskId, newStatus);
        return new ResponseEntity<>(EntityToDTO.taskEntityToDTO(updatedTask), HttpStatus.OK);
    }
}
