package com.kwizera.springbootlab11securedprojecttracker.services;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.Exceptions.EntityNotFoundException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Skill;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface UserServices {
    User register(User user) throws DuplicateRecordException;

    User findOrCreateUser(String email, User user);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(UUID id);

    User updateUser(User user, List<String> skillSet) throws EntityNotFoundException;
}
