package com.vetenrinaria.person.infrastructure.adapter.restconsumer.dto;

import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;

@Builder(toBuilder = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private String role;
    private Boolean status;
    private OffsetDateTime createdAt;
    private OffsetDateTime updateAt;
}
