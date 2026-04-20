package com.masai.dto;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class SellerCommonDto {

    @Size(max = 150, message = "Store name must not exceed 150 characters")
    private String storeName;

    private String storeDescription;
    private String storeLogoUrl;
    private String bankAccountNumber;
    private String ifscCode;
    private String accountHolderName;
    private Long businessAddressId;
}