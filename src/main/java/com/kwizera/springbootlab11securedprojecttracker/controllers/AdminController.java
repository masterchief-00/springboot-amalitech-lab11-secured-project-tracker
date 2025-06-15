package com.kwizera.springbootlab11securedprojecttracker.controllers;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.EntityNotFoundException;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.UserDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.dtos.UserUpdateRequestDTO;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;
import com.kwizera.springbootlab11securedprojecttracker.domain.mappers.EntityToDTO;
import com.kwizera.springbootlab11securedprojecttracker.services.UserServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "Admin Controller", description = "This controller exposes all endpoints involved in admin operations")
public class AdminController {

    private final UserServices userServices;

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserUpdateRequestDTO userInfo) throws EntityNotFoundException {
        Optional<User> userFound = userServices.findUserById(id);
        if (userFound.isEmpty()) throw new EntityNotFoundException("User not found");

        User user = userFound.get();
        user.setRole(userInfo.role());
        User updatedUser = userServices.updateUser(user, userInfo.skills());

        return new ResponseEntity<>(EntityToDTO.userEntityToDTO(updatedUser), HttpStatus.OK);
    }
}
