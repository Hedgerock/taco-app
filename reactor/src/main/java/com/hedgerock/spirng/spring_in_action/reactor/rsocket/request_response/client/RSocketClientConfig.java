package com.hedgerock.spirng.spring_in_action.reactor.rsocket.request_response.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;

@Configuration
@Slf4j
public class RSocketClientConfig {

    @Bean
    public ApplicationRunner requestResponseSender(RSocketRequester.Builder requestBuilder) {

        String who = "Craig";

        return args -> {
            RSocketRequester tcp = requestBuilder.tcp("localhost", 7000);

            tcp.route("greeting/{name}", who)
                    .data("Hello RSocket :)")
                    .retrieveMono(String.class)
                    .subscribe(response -> log.info("Got a response: {}", response));
        };

    }

}
