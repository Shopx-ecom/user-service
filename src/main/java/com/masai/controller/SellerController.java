package com.masai.controller;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import com.masai.core.Constants;
import com.masai.core.DefaultFilter;
import com.masai.core.FindResourceOption;
import com.masai.core.enums.SellerStatus;
import com.masai.core.enums.VerificationStatus;
import com.masai.dto.SellerRequestDto;
import com.masai.dto.SellerResponseDto;
import com.masai.dto.SellerUpdateDto;
import com.masai.models.Seller;
import com.masai.filters.SellerFilter;
import com.masai.mappers.SellerMapper;
import com.masai.service.SellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Seller Controller")
@RestController
@RequestMapping("/api/v1/sellers")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @PostMapping
    @Operation(summary = "Endpoint to add seller details")
    public ResponseEntity<SellerResponseDto> createSeller(@Valid @RequestBody SellerRequestDto dto, HttpServletRequest request) {

        Long userId = (Long) request.getAttribute(Constants.SESSION_USER_ID);
        Seller seller = SellerMapper.toEntity(dto);
        seller.setUserId(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(SellerMapper.toResponseDto(sellerService.createSeller(seller)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Endpoint to fetch seller")
    public ResponseEntity<SellerResponseDto> getSellerById(@PathVariable Long id) {
        return ResponseEntity.ok(SellerMapper.toResponseDto(sellerService.getSellerById(id)));
    }

    @GetMapping
    @Operation(summary = "Endpoint to fetch sellers with filter")
    public ResponseEntity<List<SellerResponseDto>> getAllSellers(
            @RequestParam(required = false) String storeName,
            @RequestParam(required = false) String gstNumber,
            @RequestParam(required = false) String panNumber,
            @RequestParam(required = false) Long businessAddressId,
            @RequestParam(required = false) SellerStatus sellerStatus,
            @RequestParam(required = false) VerificationStatus verificationStatus,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") Integer offset,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder
    ) {
        List<Seller> sellers = sellerService.getAllSellers(
                SellerFilter.builder()
                        .storeName(storeName)
                        .gstNumber(gstNumber)
                        .panNumber(panNumber)
                        .businessAddressId(businessAddressId)
                        .sellerStatus(sellerStatus)
                        .verificationStatus(verificationStatus)
                        .isActive(isActive)
                        .build(),
                FindResourceOption.builder()
                        .offset(offset)
                        .limit(limit)
                        .sortBy(sortBy)
                        .sortOrder(sortOrder)
                        .build(),
                DefaultFilter.builder().build()
        );
        return ResponseEntity.ok(sellers.stream().map(SellerMapper::toResponseDto).toList());
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Endpoint to update seller")
    public ResponseEntity<SellerResponseDto> updateSeller(
            @PathVariable Long id,
            @Valid @RequestBody SellerUpdateDto dto
    ) {
        Seller updated = sellerService.updateSeller(id, SellerMapper.toUpdateMap(dto));
        return ResponseEntity.ok(SellerMapper.toResponseDto(updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Endpoint to get single seller")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}
