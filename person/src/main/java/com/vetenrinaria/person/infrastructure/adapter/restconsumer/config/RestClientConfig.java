package com.vetenrinaria.person.infrastructure.adapter.restconsumer.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RestClientConfig {

    @Value("${user.service-url}")
    private String url;

    @Bean
    @LoadBalanced
    public WebClient.Builder webClient() {
        return WebClient.builder().baseUrl(url);
    }

}
