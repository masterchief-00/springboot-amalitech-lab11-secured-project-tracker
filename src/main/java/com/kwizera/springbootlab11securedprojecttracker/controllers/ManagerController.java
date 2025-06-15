package com.kwizera.springbootlab11securedprojecttracker.controllers;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.Exceptions.EntityNotFoundException;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.CreateProjectDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.ProjectDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.TaskCreateDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.TaskDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Project;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Task;
import com.kwizera.springbootlab11securedprojecttracker.domain.mappers.EntityToDTO;
import com.kwizera.springbootlab11securedprojecttracker.services.LogServices;
import com.kwizera.springbootlab11securedprojecttracker.services.ProjectServices;
import com.kwizera.springbootlab11securedprojecttracker.services.TaskServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
@Tag(name = "Manager Controller", description = "This controller exposes all endpoints involved in manager operations")
public class ManagerController {
    private final ProjectServices projectServices;
    private final TaskServices taskServices;
    private final LogServices logServices;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@Valid @RequestBody CreateProjectDTO projectDetails) throws DuplicateRecordException {
        Project createdProject = projectServices.createProject(
                Project.builder()
                        .name(projectDetails.name())
                        .description(projectDetails.description())
                        .deadline(LocalDate.parse(projectDetails.deadline()))
                        .build(),
                projectDetails.developers()
        );

        logServices.log(
                "Create",
                "Project",
                createdProject.getId().toString(),
                "Manager",
                Map.of("name", createdProject.getName(),
                        "description", createdProject.getDescription()
                )
        );

        return new ResponseEntity<>(EntityToDTO.projectEntityToDTO(createdProject), HttpStatus.CREATED);
    }

    @PostMapping("/{projectId}/task")
    public ResponseEntity<TaskDTO> createTask(@PathVariable UUID projectId, @Valid @RequestBody TaskCreateDTO taskDetails) throws DuplicateRecordException, EntityNotFoundException {
        Task task = taskServices.createTask(projectId,
                Task.builder()
                        .title(taskDetails.title())
                        .description(taskDetails.description())
                        .build()
        );

        logServices.log(
                "Create",
                "Task",
                task.getId().toString(),
                "Manager",
                Map.of("name", task.getTitle(),
                        "description", task.getDescription()
                )
        );

        return new ResponseEntity<>(EntityToDTO.taskEntityToDTO(task), HttpStatus.CREATED);
    }
}
