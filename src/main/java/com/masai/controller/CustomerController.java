package com.masai.controller;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import com.masai.core.Constants;
import com.masai.core.DefaultFilter;
import com.masai.core.FindResourceOption;
import com.masai.core.enums.AccountStatus;
import com.masai.core.enums.Language;
import com.masai.core.enums.PaymentMethod;
import com.masai.dto.CustomerRequestDto;
import com.masai.dto.CustomerResponseDto;
import com.masai.dto.CustomerUpdateDto;
import com.masai.models.Customer;
import com.masai.filters.CustomerFilter;
import com.masai.mappers.CustomerMapper;
import com.masai.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Customer Controller")
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Endpoint to add Customer details")
    public ResponseEntity<CustomerResponseDto> createCustomer(
            @Valid @RequestBody CustomerRequestDto dto,
            HttpServletRequest request
            ) {

        Long userId = (Long) request.getAttribute(Constants.SESSION_USER_ID);

        Customer customer =CustomerMapper.toEntity(dto);
        customer.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerMapper.toResponseDto(customerService.createCustomer(customer)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint to fetch Customer")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable Long id) {
        return ResponseEntity.ok(CustomerMapper.toResponseDto(customerService.getCustomerById(id)));
    }

    @GetMapping
    @Operation(summary = "Endpoint to fetch Customers with filter")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) PaymentMethod preferredPaymentMethod,
            @RequestParam(required = false) Language languagePreference,
            @RequestParam(required = false) AccountStatus accountStatus,
            @RequestParam(required = false) Boolean isPrimeMember,
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder
    ) {
        List<Customer> customers = customerService.getAllCustomers(
                CustomerFilter.builder()
                        .userId(userId)
                        .preferredPaymentMethod(preferredPaymentMethod)
                        .languagePreference(languagePreference)
                        .accountStatus(accountStatus)
                        .isPrimeMember(isPrimeMember)
                        .build(),
                FindResourceOption.builder()
                        .offset(offset)
                        .limit(limit)
                        .sortBy(sortBy)
                        .sortOrder(sortOrder)
                        .build(),
                DefaultFilter.builder().build()
        );
        return ResponseEntity.ok(customers.stream().map(CustomerMapper::toResponseDto).toList());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Endpoint to update Customer")
    public ResponseEntity<CustomerResponseDto> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerUpdateDto dto
    ) {
        Customer updated = customerService.updateCustomer(id, CustomerMapper.toUpdateMap(dto));
        return ResponseEntity.ok(CustomerMapper.toResponseDto(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint to delete Customer")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
