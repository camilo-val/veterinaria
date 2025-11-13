package com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PetResponse {
    private Long id;
    private String name;
    private Integer age;
    private String specie;
    private String race;
    private Long personId;
}
