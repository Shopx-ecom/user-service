package com.masai.dto;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CustomerResponseDto extends CustomerCommonDto {

    private Long id;
    private Long userId;
    private Integer loyaltyPoints;
    private Double totalSpent;
    private Integer totalOrders;
}