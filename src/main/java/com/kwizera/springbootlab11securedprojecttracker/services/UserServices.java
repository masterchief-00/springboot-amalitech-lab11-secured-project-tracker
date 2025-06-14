package com.kwizera.springbootlab11securedprojecttracker.services;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserServices {
    User register(User user) throws DuplicateRecordException;

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(UUID id);

    User updateUser(UUID userId, User user);
}
