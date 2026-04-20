package com.masai.repository;

import com.masai.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
public interface SellerRepository extends JpaRepository<Seller, Long>, JpaSpecificationExecutor<Seller> {
}
