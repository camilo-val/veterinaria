package com.veterinaria.authentication.infrastructure.drivenadapter.restconsumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("{user.service-url}")
    private String url;

    @LoadBalanced
    @Bean
    public WebClient.Builder builder(){
        return WebClient.builder().baseUrl(url);

    }
}
