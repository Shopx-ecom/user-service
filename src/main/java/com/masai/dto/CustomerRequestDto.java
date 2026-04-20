package com.masai.dto;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerRequestDto extends CustomerCommonDto {

    @NotNull(message = "User ID is required")
    private Long userId;
}