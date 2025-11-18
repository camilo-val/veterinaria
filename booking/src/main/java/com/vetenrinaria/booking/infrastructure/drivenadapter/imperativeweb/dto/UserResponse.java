package com.vetenrinaria.booking.infrastructure.drivenadapter.imperativeweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String role;
    private Boolean status;
}
