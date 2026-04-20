package com.masai.dto;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SellerRequestDto extends SellerCommonDto {
    @Pattern(regexp = "^[0-9]{15}$", message = "Invalid GST number")
    private String gstNumber;

    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}", message = "Invalid PAN number")
    private String panNumber;
}