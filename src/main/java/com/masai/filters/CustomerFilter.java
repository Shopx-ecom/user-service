package com.masai.filters;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */

import com.masai.core.enums.AccountStatus;
import com.masai.core.enums.Language;
import com.masai.core.enums.PaymentMethod;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CustomerFilter {

    private Long id;
    private List<Long> ids;

    private Long userId;

    private PaymentMethod preferredPaymentMethod;
    private Language languagePreference;
    private AccountStatus accountStatus;
    private Boolean isPrimeMember;
}
