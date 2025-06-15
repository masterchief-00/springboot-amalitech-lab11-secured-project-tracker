package com.kwizera.springbootlab11securedprojecttracker.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Test Controller", description = "This controller tests if the user is being authenticated")
public class AuthTestController {
    @GetMapping("/me")
    public Map<String, Object> getCurrentUser(@AuthenticationPrincipal OAuth2User user) {
        return Map.of(
                "email", user.getAttribute("email"),
                "name", user.getAttribute("name"),
                "jwt", user.getAttribute("jwt")
        );
    }
}
