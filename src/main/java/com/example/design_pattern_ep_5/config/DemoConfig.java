package com.example.design_pattern_ep_5.config;

import com.example.design_pattern_ep_5.proxy.DemoProxy;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.concurrent.TimeoutException;

@Configuration
@EnableRetry
public class DemoConfig {

    private final DemoProxy demoProxy;

    public DemoConfig(DemoProxy demoProxy) {
        this.demoProxy = demoProxy;
    }

    /**
     * Retryable should only be used to when you are encountered with below issues
     * 1. Remote calls -Sometimes we don't received response on third party apis which is common in big systems and
     *      retryable can be very helpful there
     * 2. Temporary Network losses - Intermittent network are very common to use this functionality
     *
     *
     * Don't use
     * 1. Database connection issues like deadlock scenarios
     *
     */
    @Retryable(value = {TimeoutException.class},maxAttempts = 3, backoff = @Backoff(multiplier = 2, delay = 100))
    public String demo(){
        System.out.println(":))))");
        return demoProxy.restTemplate().getForObject("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
    }


    @Recover()
    public String fallbackMethod(TimeoutException timeoutEx){
        System.out.println("Stubbed data of failed method here");
        return "stubbed data";
    }
}
