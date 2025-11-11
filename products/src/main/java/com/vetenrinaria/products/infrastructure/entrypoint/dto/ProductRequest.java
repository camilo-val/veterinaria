package com.vetenrinaria.products.infrastructure.entrypoint.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@ToString
public class ProductRequest {
    private Long id;
    private String productName;
    private String description;
    private int stock;
}
