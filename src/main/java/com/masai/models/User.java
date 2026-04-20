package com.masai.models;

/**
 * @author Sameer Shaikh
 * @date 30-03-2026
 * @description
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.masai.core.BaseEntity;
import com.masai.core.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "users")
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "username")
    private String username;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;
}