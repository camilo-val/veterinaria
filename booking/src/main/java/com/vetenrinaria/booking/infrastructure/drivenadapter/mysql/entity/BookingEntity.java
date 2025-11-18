package com.vetenrinaria.booking.infrastructure.drivenadapter.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.Instant;

@Data
@Entity(name = "booking")
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private Long employeeId;
    private Long productId;
    private Instant initialAt;
    private Instant finalAt;
    private String status;
}
