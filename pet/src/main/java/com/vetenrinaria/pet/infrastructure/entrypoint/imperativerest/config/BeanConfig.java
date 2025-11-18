package com.vetenrinaria.pet.infrastructure.entrypoint.imperativerest.config;

import com.vetenrinaria.pet.domain.model.gateway.PetGateway;
import com.vetenrinaria.pet.domain.model.person.gateway.PersonGateway;
import com.vetenrinaria.pet.domain.usecase.DeletePet;
import com.vetenrinaria.pet.domain.usecase.FindPet;
import com.vetenrinaria.pet.domain.usecase.PetUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public DeletePet deletePet(PetGateway petGateway) {
        return new DeletePet(petGateway);
    }

    @Bean
    public FindPet FindPet(PetGateway petGateway, PersonGateway personGateway) {
        return new FindPet(petGateway, personGateway);
    }

    @Bean
    public PetUseCase PetUseCase(PetGateway petGateway,PersonGateway personGateway) {
        return new PetUseCase(petGateway,personGateway);
    }
}
