package com.example.proyectoAlura2026.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfiguracionRest {


    @Bean
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }

}
