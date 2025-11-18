package com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.dto;

import com.vetenrinaria.pet.infrastructure.drivenadapter.restconsumer.dto.PersonResponse;
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
    private PersonResponse person;
}
