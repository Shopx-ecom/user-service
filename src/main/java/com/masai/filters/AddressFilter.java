package com.masai.filters;

/**
 * @author Sameer Shaikh
 * @date 31-03-2026
 * @description
 */

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class AddressFilter {

    private Long id;
    private List<Long> ids;

    private String city;
    private String state;
    private String pincode;
    private String locality;
}