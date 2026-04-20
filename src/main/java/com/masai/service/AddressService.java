package com.masai.service;

import com.masai.core.DefaultFilter;
import com.masai.core.FindResourceOption;
import com.masai.core.ResourceService;
import com.masai.models.Address;
import com.masai.filters.AddressFilter;
import com.masai.repository.AddressRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */

@Service
@RequiredArgsConstructor
public class AddressService extends ResourceService<Address> {

    private final AddressRepository addressRepository;

    @Override
    protected Class<Address> getEntityType() { return Address.class; }

    @Override
    protected JpaRepository<Address, Long> getRepository() { return addressRepository; }

    @Override
    protected JpaSpecificationExecutor<Address> getSpecificationExecutorRepository() { return addressRepository; }

    @Override
    protected String getResourceName() { return "addresses"; }

    @Override
    protected Specification<Address> getPassedFilters(Object filters, DefaultFilter defaultFilter) {

        Specification<Address> parentSpec = super.getPassedFilters(filters, defaultFilter);

        AddressFilter filter = (AddressFilter) filters;

        Specification<Address> childSpec = (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filter.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filter.getId()));

            if (filter.getIds() != null && !filter.getIds().isEmpty())
                predicate = cb.and(predicate, root.get("id").in(filter.getIds()));

            if (filter.getLocality() != null)
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("locality")), "%" + filter.getLocality().toLowerCase() + "%"));

            if (filter.getCity() != null)
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("city")), filter.getCity().toLowerCase()));

            if (filter.getState() != null)
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("state")), filter.getState().toLowerCase()));

            if (filter.getPincode() != null)
                predicate = cb.and(predicate, cb.equal(root.get("pincode"), filter.getPincode()));

            return predicate;
        };

        return Specification.where(parentSpec).and(childSpec);
    }

    public Address createAddress(Address address) {
        return create(address, Map.of());
    }

    public Address getAddressById(Long id) {
        return findResource(id);
    }

    public List<Address> getAllAddresses(AddressFilter filter, FindResourceOption option, DefaultFilter defaultFilter) {
        return findResources(filter, option, defaultFilter).getData();
    }

    public List<Address> getAllAddresses() {
        return findResources(
                AddressFilter.builder().build(),
                FindResourceOption.builder().build(),
                DefaultFilter.builder().build()
        ).getData();
    }

    public Long countAddresses(AddressFilter filter, DefaultFilter defaultFilter) {
        return countResources(filter, defaultFilter);
    }

    public Address updateAddress(Long id, Map<String, Object> updates) {
        return update(id, updates, java.util.Optional.empty());
    }

    public Address updateAddress(Long id, Map<String, Object> updates, Address existingAddress) {
        return update(id, updates, java.util.Optional.of(existingAddress));
    }

    public void deleteAddress(Long id) {
        Address address = findResource(id);
        address.setDeleted(true);
        address.setLastUpdatedAt(java.time.LocalDateTime.now(java.time.ZoneOffset.UTC));
        getRepository().save(address);
    }

    public List<Address> getByCity(String city) {
        return findResources(
                AddressFilter.builder().city(city).build(),
                FindResourceOption.builder().build(),
                DefaultFilter.builder().build()
        ).getData();
    }

    public List<Address> getByState(String state) {
        return findResources(
                AddressFilter.builder().state(state).build(),
                FindResourceOption.builder().build(),
                DefaultFilter.builder().build()
        ).getData();
    }

    public List<Address> getByPincode(String pincode) {
        return findResources(
                AddressFilter.builder().pincode(pincode).build(),
                FindResourceOption.builder().build(),
                DefaultFilter.builder().build()
        ).getData();
    }

    public List<Address> getByLocality(String locality) {
        return findResources(
                AddressFilter.builder().locality(locality).build(),
                FindResourceOption.builder().build(),
                DefaultFilter.builder().build()
        ).getData();
    }
}
