package com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingResponse {
    private Long id;
    private PersonResponse client;
    private PersonResponse employee;
    private ProductResponse product;
    private Instant initialAt;
    private Instant finalAt;
    private String status;

}
