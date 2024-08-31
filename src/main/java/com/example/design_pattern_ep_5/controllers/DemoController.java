package com.example.design_pattern_ep_5.controllers;

import com.example.design_pattern_ep_5.config.DemoConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private final DemoConfig demoConfig;
    public DemoController(DemoConfig demoConfig) {
        this.demoConfig = demoConfig;
    }

    @GetMapping("/demo")
    public String demo(){
        return demoConfig.demo();
    }
}
