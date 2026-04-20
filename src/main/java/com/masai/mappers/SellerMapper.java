package com.masai.mappers;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import com.masai.dto.SellerRequestDto;
import com.masai.dto.SellerResponseDto;
import com.masai.dto.SellerUpdateDto;
import com.masai.models.Seller;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class SellerMapper {

    public static Seller toEntity(SellerRequestDto dto) {
        return Seller.builder()
                .storeName(dto.getStoreName())
                .storeDescription(dto.getStoreDescription())
                .storeLogoUrl(dto.getStoreLogoUrl())
                .gstNumber(dto.getGstNumber())
                .panNumber(dto.getPanNumber())
                .bankAccountNumber(dto.getBankAccountNumber())
                .ifscCode(dto.getIfscCode())
                .accountHolderName(dto.getAccountHolderName())
                .businessAddressId(dto.getBusinessAddressId())
                .build();
    }

    public static SellerResponseDto toResponseDto(Seller seller) {
        return SellerResponseDto.builder()
                .id(seller.getId())
                .userId(seller.getUserId())
                .storeName(seller.getStoreName())
                .storeDescription(seller.getStoreDescription())
                .storeLogoUrl(seller.getStoreLogoUrl())
                .gstNumber(seller.getGstNumber())
                .panNumber(seller.getPanNumber())
                .bankAccountNumber(seller.getBankAccountNumber())
                .ifscCode(seller.getIfscCode())
                .accountHolderName(seller.getAccountHolderName())
                .businessAddressId(seller.getBusinessAddressId())
                .totalProducts(seller.getTotalProducts())
                .totalOrders(seller.getTotalOrders())
                .rating(seller.getRating())
                .sellerStatus(seller.getSellerStatus())
                .verificationStatus(seller.getVerificationStatus())
                .build();
    }

    public static Map<String, Object> toUpdateMap(SellerUpdateDto dto) {
        Map<String, Object> updates = new HashMap<>();
        if (dto.getStoreName() != null)         updates.put("storeName", dto.getStoreName());
        if (dto.getStoreDescription() != null)  updates.put("storeDescription", dto.getStoreDescription());
        if (dto.getStoreLogoUrl() != null)      updates.put("storeLogoUrl", dto.getStoreLogoUrl());
        if (dto.getBankAccountNumber() != null) updates.put("bankAccountNumber", dto.getBankAccountNumber());
        if (dto.getIfscCode() != null)          updates.put("ifscCode", dto.getIfscCode());
        if (dto.getAccountHolderName() != null) updates.put("accountHolderName", dto.getAccountHolderName());
        if (dto.getBusinessAddressId() != null) updates.put("businessAddressId", dto.getBusinessAddressId());
        return updates;
    }

}
