package com.vetenrinaria.user.infrastructure.entrypoint.dto;

import com.vetenrinaria.user.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String username;
    private String password;
    private Role role;
    private Boolean status;
    private Date createAt;
    private Date updateAt;
}
