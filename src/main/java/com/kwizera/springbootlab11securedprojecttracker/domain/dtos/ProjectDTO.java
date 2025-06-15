package com.kwizera.springbootlab11securedprojecttracker.domain.dtos;

import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;
import lombok.Builder;

import java.util.List;

@Builder
public record ProjectDTO(
        String name,
        String description,
        List<User> developer,
        List<TaskDTO> tasks
) {
}
