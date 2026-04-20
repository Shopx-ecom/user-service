package com.masai.models;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */

import com.masai.core.BaseEntity;
import com.masai.core.enums.Role;
import com.masai.core.enums.SellerStatus;
import com.masai.core.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "sellers")
@SuperBuilder
public class Seller extends BaseEntity {

    @Column(name="user_id",nullable = false)
    private Long userId;
    
    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "store_description")
    private String storeDescription;

    @Column(name = "store_logo_url")
    private String storeLogoUrl;

    @Column(name = "gst_number", unique = true)
    private String gstNumber;

    @Column(name = "pan_number", unique = true)
    private String panNumber;

    @Column(name = "business_address_id")
    private Long businessAddressId;

    @Column(name = "bank_account_number")
    private String bankAccountNumber;

    @Column(name = "ifsc_code")
    private String ifscCode;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @Column(name = "total_products")
    @Builder.Default
    private Long totalProducts = 0L;

    @Column(name = "total_orders")
    @Builder.Default
    private Long totalOrders = 0L;

    @Column(name = "rating")
    private Double rating;

    @Enumerated(EnumType.STRING)
    @Column(name = "seller_status")
    @Builder.Default
    private SellerStatus sellerStatus = SellerStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status")
    @Builder.Default
    private VerificationStatus verificationStatus = VerificationStatus.PENDING;

}