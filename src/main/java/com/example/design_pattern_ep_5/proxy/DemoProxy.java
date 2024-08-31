package com.example.design_pattern_ep_5.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DemoProxy {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
