package com.masai.filters;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */

import com.masai.core.enums.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserFilter {

    private Long id;
    private List<Long> ids;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String username;

    private Role role;
    private Boolean isActive;
}