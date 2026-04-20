package com.masai.service;

import com.masai.core.DefaultFilter;
import com.masai.core.FindResourceOption;
import com.masai.core.ResourceService;
import com.masai.models.Customer;
import com.masai.filters.CustomerFilter;
import com.masai.repository.CustomerRepository;
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
public class CustomerService extends ResourceService<Customer> {

    private final CustomerRepository customerRepository;

    @Override
    protected Class<Customer> getEntityType() {
        return Customer.class;
    }

    @Override
    protected JpaRepository<Customer, Long> getRepository() {
        return customerRepository;
    }

    @Override
    protected JpaSpecificationExecutor<Customer> getSpecificationExecutorRepository() {
        return customerRepository;
    }

    @Override
    protected String getResourceName() {
        return "customers";
    }

    @Override
    protected Specification<Customer> getPassedFilters(Object filters, DefaultFilter defaultFilter) {

        Specification<Customer> parentSpec = super.getPassedFilters(filters, defaultFilter);

        CustomerFilter filter = (CustomerFilter) filters;

        Specification<Customer> childSpec = (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filter.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filter.getId()));

            if (filter.getIds() != null && !filter.getIds().isEmpty())
                predicate = cb.and(predicate, root.get("id").in(filter.getIds()));

            if (filter.getUserId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("userId"), filter.getUserId()));

            if (filter.getPreferredPaymentMethod() != null)
                predicate = cb.and(predicate, cb.equal(root.get("preferredPaymentMethod"), filter.getPreferredPaymentMethod()));

            if (filter.getLanguagePreference() != null)
                predicate = cb.and(predicate, cb.equal(root.get("languagePreference"), filter.getLanguagePreference()));

            if (filter.getAccountStatus() != null)
                predicate = cb.and(predicate, cb.equal(root.get("accountStatus"), filter.getAccountStatus()));

            if (filter.getIsPrimeMember() != null)
                predicate = cb.and(predicate, cb.equal(root.get("isPrimeMember"), filter.getIsPrimeMember()));

            return predicate;
        };

        return Specification.where(parentSpec).and(childSpec);
    }

    public Customer createCustomer(Customer entity) {
        return create(entity, Map.of());
    }

    public Customer getCustomerById(Long id) {
        return findResource(id);
    }

    public List<Customer> getAllCustomers(CustomerFilter filter, FindResourceOption option, DefaultFilter defaultFilter) {
        return findResources(filter, option, defaultFilter).getData();
    }

    public Customer updateCustomer(Long id, Map<String, Object> updates) {
        return update(id, updates, Optional.empty());
    }

    public void deleteCustomer(Long id) {
        Customer customer = findResource(id);
        customer.setDeleted(true);
        customer.setLastUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        getRepository().save(customer);
    }
}
