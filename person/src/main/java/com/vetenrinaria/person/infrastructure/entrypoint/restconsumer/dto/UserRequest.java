package com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.dto;

import lombok.*;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private String role;
    private Boolean status;
    private Date createdAt;
    private Date updateAt;
}
