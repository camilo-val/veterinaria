package com.vetenrinaria.booking.domain.model.person.gateway;

import com.vetenrinaria.booking.domain.model.person.PersonWithUser;

import java.util.Optional;

public interface PersonGateway {
    Optional<PersonWithUser> findByID(Long clientId);
}
