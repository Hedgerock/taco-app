package com.hedgerock.spirng.spring_in_action.view_taco_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.client.Traverson;
import org.springframework.stereotype.Component;

import java.net.URI;


public class TraversonConfig {

    Traverson traverson() {

        return new Traverson(
                URI.create("http://localhost:8080/api")
        );

    }

}
