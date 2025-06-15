package com.kwizera.springbootlab11securedprojecttracker.domain.mappers;

import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.ProjectDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.TaskDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.UserDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Project;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Skill;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Task;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;

import java.util.stream.Collectors;

public class EntityToDTO {
    public static UserDTO userEntityToDTO(User user) {
        return UserDTO.builder()
                .names(user.getFirstName() + " " + user.getLastName())
                .email(user.getEmail())
                .skills(user.getSkills().stream().map(Skill::getName).toList())
                .tasks(user.getTasks().stream().map(
                        EntityToDTO::taskEntityToDTO
                ).collect(Collectors.toList()))
                .build();
    }

    public static TaskDTO taskEntityToDTO(Task task) {
        return TaskDTO.builder()
                .title(task.getTitle())
                .description(task.getDescription())
                .projectName(task.getProject().getName())
                .build();
    }

    public static ProjectDTO projectEntityToDTO(Project project) {
        return ProjectDTO.builder()
                .name(project.getName())
                .description(project.getDescription())
                .developer(project.getDevelopers())
                .tasks(project.getTasks().stream().map(EntityToDTO::taskEntityToDTO).toList())
                .build();
    }

}
