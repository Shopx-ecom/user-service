package com.masai.dto;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import com.masai.core.enums.AccountStatus;
import com.masai.core.enums.Language;
import com.masai.core.enums.PaymentMethod;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class CustomerCommonDto {

    private PaymentMethod preferredPaymentMethod;
    private Language languagePreference;
    private AccountStatus accountStatus;
    private Boolean isPrimeMember;
}