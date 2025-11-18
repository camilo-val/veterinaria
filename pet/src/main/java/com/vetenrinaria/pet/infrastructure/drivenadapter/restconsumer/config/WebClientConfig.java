package com.vetenrinaria.pet.infrastructure.drivenadapter.restconsumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${external-api.person.service-url}")
    private String URL;

    @LoadBalanced
    @Bean
    public WebClient.Builder webClient() {
        return WebClient.builder().baseUrl(URL);
    }
}
