package com.kwizera.springbootlab11securedprojecttracker.domain.dtos;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record TaskDTO(
        UUID id,
        String title,
        String description,
        String projectName
) {
}
