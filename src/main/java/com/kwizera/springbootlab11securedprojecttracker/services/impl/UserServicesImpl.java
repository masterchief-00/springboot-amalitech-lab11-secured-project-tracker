package com.kwizera.springbootlab11securedprojecttracker.services.impl;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;
import com.kwizera.springbootlab11securedprojecttracker.domain.enums.UserRole;
import com.kwizera.springbootlab11securedprojecttracker.repositories.UserRepository;
import com.kwizera.springbootlab11securedprojecttracker.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;

    @Override
    public User register(User user) throws DuplicateRecordException {
        Optional<User> userFound = findUserByEmail(user.getEmail());

        if (userFound.isPresent())
            throw new DuplicateRecordException("User with email " + user.getEmail() + " already exists");

        user.setRole(UserRole.CONTRACTOR);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return userRepository.findById(id);
    }
}
