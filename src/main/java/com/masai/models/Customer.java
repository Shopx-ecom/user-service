package com.masai.models;

import com.masai.core.BaseEntity;
import com.masai.core.enums.AccountStatus;
import com.masai.core.enums.Language;
import com.masai.core.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "customers")
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends BaseEntity{

    @Column(name = "user_id")
    public Long userId;

    @Column(name = "loyalty_points", columnDefinition = "INT DEFAULT 0")
    @Builder.Default
    private Integer loyaltyPoints = 0;

    @Column(name = "total_spent", columnDefinition = "DECIMAL(12,2) DEFAULT 0")
    @Builder.Default
    private Double totalSpent = 0.0;

    @Column(name = "total_orders", columnDefinition = "INT DEFAULT 0")
    @Builder.Default
    private Integer totalOrders = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "preferred_payment_method")
    private PaymentMethod preferredPaymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "language_preference")
    private Language languagePreference;

    @Column(name = "is_prime_member")
    @Builder.Default
    private Boolean isPrimeMember = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_status")
    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.ACTIVE;

}
