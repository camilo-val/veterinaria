package com.vetenrinaria.pet.domain.model.person.gateway;


import com.vetenrinaria.pet.domain.model.person.PersonWithUser;

import java.util.List;
import java.util.Optional;

public interface PersonGateway {
    Optional<PersonWithUser> findByID(Long clientId);
    List<PersonWithUser> findAll();
}
