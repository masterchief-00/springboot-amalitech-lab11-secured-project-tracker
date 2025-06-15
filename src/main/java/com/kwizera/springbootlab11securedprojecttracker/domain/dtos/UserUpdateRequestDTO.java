package com.kwizera.springbootlab11securedprojecttracker.domain.dtos;

import com.kwizera.springbootlab11securedprojecttracker.domain.enums.UserRole;

import java.util.List;

public record UserUpdateRequestDTO(
        List<String> skills,
        UserRole role
) {
}
