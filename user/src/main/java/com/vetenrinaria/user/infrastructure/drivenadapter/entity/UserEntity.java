package com.vetenrinaria.user.infrastructure.drivenadapter.entity;

//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
import lombok.*;
//import org.springframework.data.annotation.Id;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class UserEntity {

   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String role;
    private Boolean status;
    private Date createAt;
    private Date updateAt;
}
