package com.kwizera.springbootlab11securedprojecttracker.services.impl;

import com.kwizera.springbootlab11securedprojecttracker.Exceptions.DuplicateRecordException;
import com.kwizera.springbootlab11securedprojecttracker.Exceptions.EntityNotFoundException;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.Skill;
import com.kwizera.springbootlab11securedprojecttracker.domain.entities.User;
import com.kwizera.springbootlab11securedprojecttracker.domain.enums.UserRole;
import com.kwizera.springbootlab11securedprojecttracker.repositories.UserRepository;
import com.kwizera.springbootlab11securedprojecttracker.services.SkillServices;
import com.kwizera.springbootlab11securedprojecttracker.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;
    private final SkillServices skillServices;

    @Override
    public User register(User user) throws DuplicateRecordException {
        Optional<User> userFound = findUserByEmail(user.getEmail());

        if (userFound.isPresent())
            throw new DuplicateRecordException("User with email " + user.getEmail() + " already exists");

        user.setRole(UserRole.CONTRACTOR);

        return userRepository.save(user);
    }

    @Override
    public User findOrCreateUser(String email, User user) {
        Optional<User> userFound = findUserByEmail(user.getEmail());

        if (userFound.isEmpty()) {
            user.setRole(UserRole.CONTRACTOR);

            return userRepository.save(user);
        }

        return userFound.get();
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user, List<String> skillSet) throws EntityNotFoundException {
        Set<Skill> userSkills = skillSet.stream()
                .map(skillServices::findOrCreateSkill)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        user.setSkills(userSkills);

        return userRepository.save(user);
    }
}
