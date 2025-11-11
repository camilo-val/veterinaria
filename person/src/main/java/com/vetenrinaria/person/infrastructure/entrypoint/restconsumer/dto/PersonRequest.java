package com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.dto;

import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonRequest {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private UserRequest user;
}
