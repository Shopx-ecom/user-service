package com.masai.dto;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import com.masai.core.enums.Role;
import com.masai.core.enums.SellerStatus;
import com.masai.core.enums.VerificationStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SellerResponseDto extends SellerCommonDto {

    private Long id;
    private Long userId;
    private String username;
    private Role role;
    private String gstNumber;
    private String panNumber;
    private Long totalProducts;
    private Long totalOrders;
    private Double rating;
    private SellerStatus sellerStatus;
    private VerificationStatus verificationStatus;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdatedAt;
}