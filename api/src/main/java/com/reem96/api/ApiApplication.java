package com.reem96.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

// Created by Shorasul Sh. on 29.07.2020

@EntityScan(value = {"com.reem96.domain.entities"})
@EnableJpaRepositories(value = {"com.reem96.domain.repositories"})
@SpringBootApplication(scanBasePackages = "com.reem96.api")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
