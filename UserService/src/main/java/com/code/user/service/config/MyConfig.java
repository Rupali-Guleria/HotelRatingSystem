package com.code.user.service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyConfig {

    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate;


    }
}
