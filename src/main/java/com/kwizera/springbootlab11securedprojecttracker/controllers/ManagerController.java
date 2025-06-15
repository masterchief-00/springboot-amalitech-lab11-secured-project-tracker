package com.kwizera.springbootlab11securedprojecttracker.controllers;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.CreateProjectDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.ProjectDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Project;
import com.kwizera.springbootlab11securedprojecttracker.domain.mappers.EntityToDTO;
import com.kwizera.springbootlab11securedprojecttracker.services.ProjectServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ManagerController {
    private final ProjectServices projectServices;

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody CreateProjectDTO projectDetails) throws DuplicateRecordException {
        Project createdProject = projectServices.createProject(
                Project.builder()
                        .name(projectDetails.name())
                        .description(projectDetails.description())
                        .deadline(LocalDate.parse(projectDetails.deadline()))
                        .build(),
                projectDetails.developers()
        );

        return new ResponseEntity<>(EntityToDTO.projectEntityToDTO(createdProject), HttpStatus.CREATED);
    }
}
