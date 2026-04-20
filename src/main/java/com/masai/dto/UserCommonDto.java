package com.masai.dto;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class UserCommonDto {

    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @Pattern(regexp = "^[0-9]{10,15}$", message = "Invalid phone number")
    private String phoneNumber;

    private String profilePictureUrl;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;
}