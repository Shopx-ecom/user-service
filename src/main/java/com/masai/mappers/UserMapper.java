package com.masai.mappers;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */

import com.masai.dto.UserRequestDto;
import com.masai.dto.UserResponseDto;
import com.masai.dto.UserUpdateDto;
import com.masai.models.User;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class UserMapper {

    public static User toEntity(UserRequestDto dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .profilePictureUrl(dto.getProfilePictureUrl())
                .dateOfBirth(dto.getDateOfBirth())
                .role(dto.getRole())
                .build();
    }

    public static UserResponseDto toResponseDto(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .profilePictureUrl(user.getProfilePictureUrl())
                .dateOfBirth(user.getDateOfBirth())
                .role(user.getRole())
                .isActive(user.getIsActive())
                .build();
    }

    public static Map<String, Object> toUpdateMap(UserUpdateDto dto) {
        Map<String, Object> updates = new HashMap<>();
        if (dto.getFirstName() != null)         updates.put("firstName", dto.getFirstName());
        if (dto.getLastName() != null)          updates.put("lastName", dto.getLastName());
        if (dto.getPhoneNumber() != null)       updates.put("phoneNumber", dto.getPhoneNumber());
        if (dto.getProfilePictureUrl() != null) updates.put("profilePictureUrl", dto.getProfilePictureUrl());
        if (dto.getDateOfBirth() != null)       updates.put("dateOfBirth", dto.getDateOfBirth());
        return updates;
    }

}