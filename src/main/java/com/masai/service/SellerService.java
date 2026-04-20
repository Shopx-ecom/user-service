package com.masai.service;

import com.masai.core.DefaultFilter;
import com.masai.core.FindResourceOption;
import com.masai.core.ResourceService;
import com.masai.models.Seller;
import com.masai.filters.SellerFilter;
import com.masai.repository.SellerRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */

@RequiredArgsConstructor
@Service
public class SellerService extends ResourceService<Seller> {

    private final SellerRepository sellerRepository;

    @Override
    protected Class<Seller> getEntityType() {
        return Seller.class;
    }

    @Override
    protected JpaRepository<Seller, Long> getRepository() {
        return sellerRepository;
    }

    @Override
    protected JpaSpecificationExecutor<Seller> getSpecificationExecutorRepository() {
        return sellerRepository;
    }

    @Override
    protected String getResourceName() {
        return "sellers";
    }

    @Override
    protected Specification<Seller> getPassedFilters(Object filters, DefaultFilter defaultFilter) {

        Specification<Seller> parentSpec = super.getPassedFilters(filters, defaultFilter);

        SellerFilter filter = (SellerFilter) filters;

        Specification<Seller> childSpec = (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filter.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filter.getId()));

            if (filter.getIds() != null && !filter.getIds().isEmpty())
                predicate = cb.and(predicate, root.get("id").in(filter.getIds()));

            if (filter.getStoreName() != null)
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("storeName")), "%" + filter.getStoreName().toLowerCase() + "%"));

            if (filter.getGstNumber() != null)
                predicate = cb.and(predicate, cb.equal(root.get("gstNumber"), filter.getGstNumber()));

            if (filter.getPanNumber() != null)
                predicate = cb.and(predicate, cb.equal(root.get("panNumber"), filter.getPanNumber()));

            if (filter.getBusinessAddressId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("businessAddressId"), filter.getBusinessAddressId()));

            if (filter.getSellerStatus() != null)
                predicate = cb.and(predicate, cb.equal(root.get("sellerStatus"), filter.getSellerStatus()));

            if (filter.getVerificationStatus() != null)
                predicate = cb.and(predicate, cb.equal(root.get("verificationStatus"), filter.getVerificationStatus()));

            if (filter.getIsActive() != null)
                predicate = cb.and(predicate, cb.equal(root.get("isActive"), filter.getIsActive()));

            return predicate;
        };

        return Specification.where(parentSpec).and(childSpec);
    }

    public Seller createSeller(Seller entity) {
        return create(entity, Map.of());
    }

    public Seller getSellerById(Long id) {
        return findResource(id);
    }

    public List<Seller> getAllSellers(SellerFilter filter, FindResourceOption option, DefaultFilter defaultFilter) {
        return findResources(filter, option, defaultFilter).getData();
    }

    public Seller updateSeller(Long id, Map<String, Object> updates) {
        return update(id, updates, Optional.empty());
    }

    public void deleteSeller(Long id) {
        Seller seller = findResource(id);
        seller.setDeleted(true);
        seller.setLastUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        getRepository().save(seller);
    }
}
