package com.masai.controller;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import com.masai.core.DefaultFilter;
import com.masai.core.FindResourceOption;
import com.masai.core.PageResponse;
import com.masai.core.enums.Role;
import com.masai.dto.UserRequestDto;
import com.masai.dto.UserResponseDto;
import com.masai.dto.UserUpdateDto;
import com.masai.models.User;
import com.masai.filters.UserFilter;
import com.masai.mappers.UserMapper;
import com.masai.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User Controller")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint to get single user")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(UserMapper.toResponseDto(userService.getUserById(id)));
    }

    @GetMapping
    @Operation(summary = "Endpoint to fetch users with filter.")
    public ResponseEntity<PageResponse<UserResponseDto>> getAllUsers(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "desc") String sortOrder
    ) {
        PageResponse<User> users = userService.getAllUsers(
                UserFilter.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(email)
                        .username(username)
                        .phoneNumber(phoneNumber)
                        .role(role)
                        .isActive(isActive)
                        .build(),
                FindResourceOption.builder()
                        .offset(page)
                        .limit(size)
                        .sortOrder(sortOrder)
                        .build(),
                DefaultFilter.builder().build()
        );

        return ResponseEntity.ok(
               users.map(UserMapper::toResponseDto)
        );
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Endpoint to update user data.")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateDto dto
    ) {
        User updated = userService.updateUser(id, UserMapper.toUpdateMap(dto));
        return ResponseEntity.ok(UserMapper.toResponseDto(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint to delete single user")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
