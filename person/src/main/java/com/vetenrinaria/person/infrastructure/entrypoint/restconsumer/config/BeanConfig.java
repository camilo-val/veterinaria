package com.vetenrinaria.person.infrastructure.entrypoint.restconsumer.config;

import com.vetenrinaria.person.domain.model.gateway.PersonGateway;
import com.vetenrinaria.person.domain.model.gateway.UserGateway;
import com.vetenrinaria.person.domain.usecase.DeletePerson;
import com.vetenrinaria.person.domain.usecase.FindPersonUseCase;
import com.vetenrinaria.person.domain.usecase.PersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    @Bean
    public FindPersonUseCase findPersonUseCase(PersonGateway personGateway, UserGateway userGateway) {
        return new FindPersonUseCase(personGateway, userGateway);
    }

    @Bean
    public PersonUseCase PersonUseCase(PersonGateway personGateway, UserGateway userGateway) {
        return new PersonUseCase(personGateway, userGateway);
    }

    @Bean
    public DeletePerson deletePerson(PersonGateway personGateway, UserGateway userGateway){
        return new DeletePerson(personGateway,userGateway);

    }
}
