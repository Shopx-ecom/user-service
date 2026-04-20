package com.masai.mappers;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import com.masai.dto.CustomerRequestDto;
import com.masai.dto.CustomerResponseDto;
import com.masai.dto.CustomerUpdateDto;
import com.masai.models.Customer;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
public class CustomerMapper {

    public static Customer toEntity(CustomerRequestDto dto) {
        return Customer.builder()
                .userId(dto.getUserId())
                .preferredPaymentMethod(dto.getPreferredPaymentMethod())
                .languagePreference(dto.getLanguagePreference())
                .accountStatus(dto.getAccountStatus())
                .isPrimeMember(dto.getIsPrimeMember() != null ? dto.getIsPrimeMember() : false)
                .build();
    }

    public static CustomerResponseDto toResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .userId(customer.getUserId())
                .preferredPaymentMethod(customer.getPreferredPaymentMethod())
                .languagePreference(customer.getLanguagePreference())
                .accountStatus(customer.getAccountStatus())
                .isPrimeMember(customer.getIsPrimeMember())
                .loyaltyPoints(customer.getLoyaltyPoints())
                .totalSpent(customer.getTotalSpent())
                .totalOrders(customer.getTotalOrders())
                .build();
    }

    public static Map<String, Object> toUpdateMap(CustomerUpdateDto dto) {
        Map<String, Object> updates = new HashMap<>();
        if (dto.getPreferredPaymentMethod() != null) updates.put("preferredPaymentMethod", dto.getPreferredPaymentMethod());
        if (dto.getLanguagePreference() != null)     updates.put("languagePreference", dto.getLanguagePreference());
        if (dto.getAccountStatus() != null)          updates.put("accountStatus", dto.getAccountStatus());
        if (dto.getIsPrimeMember() != null)          updates.put("isPrimeMember", dto.getIsPrimeMember());
        return updates;
    }

}
