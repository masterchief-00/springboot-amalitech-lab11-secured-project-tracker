package com.kwizera.springbootlab11securedprojecttracker.services;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;

import java.util.Optional;

public interface UserServices {
    User register(User user) throws DuplicateRecordException;

    Optional<User> findUserByEmail(String email);
}
