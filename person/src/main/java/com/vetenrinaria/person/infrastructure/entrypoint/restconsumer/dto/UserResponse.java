package com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String role;
    private Boolean status;
    private Date createAt;
    private Date updateAt;
}
