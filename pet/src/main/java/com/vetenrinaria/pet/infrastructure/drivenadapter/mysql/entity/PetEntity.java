package com.vetenrinaria.pet.infrastructure.drivenadapter.mysql.entity;

import com.vetenrinaria.pet.domain.model.exceptions.BusinessExceptions;
import com.vetenrinaria.pet.domain.model.exceptions.BusinessMessageExceptions;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "pet")
public final class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String specie;
    private String race;
    private Long personId;
}
