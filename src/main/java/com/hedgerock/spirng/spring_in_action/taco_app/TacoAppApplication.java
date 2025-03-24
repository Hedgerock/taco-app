package com.hedgerock.spirng.spring_in_action.taco_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@SpringBootApplication
public class TacoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoAppApplication.class, args);
    }

    @Bean
    ErrorViewResolver supportPathBasedLocationStrategyWithoutHashes() {
        return  (request, status, model) ->
           status == HttpStatus.NOT_FOUND
                    ? new ModelAndView("index.html", Collections.emptyMap(), HttpStatus.OK)
                    : null;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
