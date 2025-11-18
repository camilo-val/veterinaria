package com.vetenrinaria.booking.domain.model.person.gateway;

import com.vetenrinaria.booking.domain.model.person.PersonWithUser;

import java.util.List;
import java.util.Optional;

public interface PersonGateway {
    List<PersonWithUser> findAll();
    Optional<PersonWithUser> findByID(Long clientId);
}
