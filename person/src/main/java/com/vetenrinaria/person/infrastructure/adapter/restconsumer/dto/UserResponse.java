package com.vetenrinaria.person.infrastructure.adapter.restconsumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String role;
    private Boolean status;
    private Date createdAt;
    private Date updateAt;
}
