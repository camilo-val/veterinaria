package com.vetenrinaria.user.infrastructure.entrypoint.dto;

import com.vetenrinaria.user.domain.model.Role;
import lombok.*;

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
    private Role role;
    private Boolean status;
    private Date createAt;
    private Date updateAt;
}
