package com.masai.controller;

import com.masai.config.JwtUtil;
import com.masai.core.enums.Role;
import com.masai.models.User;
import com.masai.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sameer Shaikh
 * @date 04-04-2026
 * @description
 */

@Tag(name = "Auth Controller")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Endpoint to login user")
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestParam String email,
            @RequestParam String password
    ) {
        String token = authService.login(email, password);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Endpoint to register user")
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam Role role
    ) {
        String response = authService.register(firstName, lastName, email, password, role);
        return ResponseEntity.status(201).body(response); // CREATED
    }

}