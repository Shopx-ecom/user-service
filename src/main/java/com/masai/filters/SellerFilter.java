package com.masai.filters;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */

import com.masai.core.enums.SellerStatus;
import com.masai.core.enums.VerificationStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SellerFilter {

    private Long id;
    private List<Long> ids;

    private String storeName;
    private String gstNumber;
    private String panNumber;
    private Long businessAddressId;

    private SellerStatus sellerStatus;
    private VerificationStatus verificationStatus;
    private Boolean isActive;
}