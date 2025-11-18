package com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.dto;

import lombok.*;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingRequest {
    private Long id;
    private Long clientId;
    private Long employeeId;
    private Long productId;
    private Instant initialAt;
    private Instant finalAt;
    private String status;

}
