package com.masai.service;

import com.masai.core.DefaultFilter;
import com.masai.core.FindResourceOption;
import com.masai.core.PageResponse;
import com.masai.core.ResourceService;
import com.masai.models.User;

import com.masai.filters.UserFilter;
import com.masai.repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService extends ResourceService<User> implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    protected Class<User> getEntityType() {
        return User.class;
    }

    @Override
    protected JpaRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    protected JpaSpecificationExecutor<User> getSpecificationExecutorRepository() {
        return userRepository;
    }

    @Override
    protected String getResourceName() {
        return "users";
    }

    @Override
    protected Specification<User> getPassedFilters(Object filters, DefaultFilter defaultFilter) {

        Specification<User> parentSpec = super.getPassedFilters(filters, defaultFilter);

        UserFilter filter = (UserFilter) filters;

        Specification<User> childSpec = (root, query, cb) -> {
            Predicate predicate = cb.conjunction();

            if (filter.getId() != null)
                predicate = cb.and(predicate, cb.equal(root.get("id"), filter.getId()));

            if (filter.getIds() != null && !filter.getIds().isEmpty())
                predicate = cb.and(predicate, root.get("id").in(filter.getIds()));

            if (filter.getFirstName() != null)
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("firstName")), "%" + filter.getFirstName().toLowerCase() + "%"));

            if (filter.getLastName() != null)
                predicate = cb.and(predicate, cb.like(cb.lower(root.get("lastName")), "%" + filter.getLastName().toLowerCase() + "%"));

            if (filter.getEmail() != null)
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("email")), filter.getEmail().toLowerCase()));

            if (filter.getPhoneNumber() != null)
                predicate = cb.and(predicate, cb.equal(root.get("phoneNumber"), filter.getPhoneNumber()));

            if (filter.getUsername() != null)
                predicate = cb.and(predicate, cb.equal(cb.lower(root.get("username")), filter.getUsername().toLowerCase()));

            if (filter.getRole() != null)
                predicate = cb.and(predicate, cb.equal(root.get("role"), filter.getRole()));

            if (filter.getIsActive() != null)
                predicate = cb.and(predicate, cb.equal(root.get("isActive"), filter.getIsActive()));

            return predicate;
        };

        return Specification.where(parentSpec).and(childSpec);
    }

    public User createUser(User user) {
        return create(user, Map.of());
    }

    public User getUserById(Long id) {
        return findResource(id);
    }

    public PageResponse<User> getAllUsers(UserFilter filter, FindResourceOption option, DefaultFilter defaultFilter) {
        return findResources(filter, option, defaultFilter);
    }

    public User updateUser(Long id, Map<String, Object> updates) {
        return update(id, updates, Optional.empty());
    }

    public void deleteUser(Long id) {
        User user = findResource(id);
        user.setDeleted(true);
        user.setLastUpdatedAt(LocalDateTime.now(ZoneOffset.UTC));
        getRepository().save(user);
    }

    public UserDetails loadUserById(Long userId){

        User user = findResource(userId);

        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(user.getRole().name());

        return new org.springframework.security.core.userdetails.User(
                user.getId().toString(),
                user.getPassword(),
                List.of(authority)
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        List<User> userList = findResources(
                UserFilter.builder()
                        .email(email)
                        .build(),
                FindResourceOption.builder().build(),
                DefaultFilter.builder().build()
        ).getData();

        if (userList.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        User user = userList.getFirst();
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(user.getRole().name());

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                List.of(authority)
        );
    }

    public boolean existsByEmail(String email) {
        return !findResources(
                UserFilter.builder()
                        .email(email)
                        .build(),
                FindResourceOption.builder().build(),
                DefaultFilter.builder().build()
        ).getData().isEmpty();
    }
}
