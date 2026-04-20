package com.masai.service;

import com.masai.config.JwtUtil;
import com.masai.core.DefaultFilter;
import com.masai.core.FindResourceOption;
import com.masai.core.enums.Role;
import com.masai.exception.AuthException;
import com.masai.exception.NotFoundException;
import com.masai.filters.UserFilter;
import com.masai.models.User;
import com.masai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sameer Shaikh
 * @date 04-04-2026
 * @description
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public String login(String email, String password) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        UserDetails userDetails =
                userService.loadUserByUsername(email);

        List<User> users = userService.findResources(
                UserFilter.builder()
                        .email(email)
                        .build(),
                FindResourceOption.builder().build(),
                DefaultFilter.builder().build()
        ).getData();

        if(users.isEmpty()) throw new NotFoundException("User not found!");

        return jwtUtil.generateToken(userDetails,users.getFirst().getId());
    }

    public String register(
            String firstName,
            String lastName,
            String email,
            String password,
            Role role
    ) {

        if (userService.existsByEmail(email)) {
            throw new AuthException("User already exists");
        }

        userService.createUser(
                User.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .password(passwordEncoder.encode(password))
                        .role(role)
                        .build()
        );

        return "User registered successfully";
    }
}