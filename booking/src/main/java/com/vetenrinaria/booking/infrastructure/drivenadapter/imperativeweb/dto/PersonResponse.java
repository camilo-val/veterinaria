package com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class PersonResponse {
    private Long id;
    private String name;
    private String email;
    private UserResponse user;
}
