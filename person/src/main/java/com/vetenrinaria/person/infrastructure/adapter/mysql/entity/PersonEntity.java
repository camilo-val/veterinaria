package com.vetenrinaria.person.infrastructure.adapter.mysql.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "person") // 👈 agrega esto en lugar de (name="person")
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Long userId; // 👈 este campo sí existe
}
