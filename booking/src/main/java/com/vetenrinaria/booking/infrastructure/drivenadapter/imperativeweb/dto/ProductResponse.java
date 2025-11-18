package com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
    private Long id;
    private String productName;
    private String description;
}
