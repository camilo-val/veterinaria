package com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String phone;
    private String address;
    @JsonIgnore
    private UserResponse user;
}
