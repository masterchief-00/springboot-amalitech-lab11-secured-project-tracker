package com.kwizera.springbootlab11securedprojecttracker.domain.dtos;

import com.kwizera.springbootlab11securedprojecttracker.domain.enums.UserRole;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
public record UserDTO(
        UUID id,
        String names,
        String email,
        UserRole role,
        List<String> skills,
        List<TaskDTO> tasks
) {
}
