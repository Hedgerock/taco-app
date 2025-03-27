package com.hedgerock.spirng.spring_in_action.reactor.rsocket.request_response.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@Slf4j
public class HelloController {

    @MessageMapping("greeting/{name}")
    public Mono<String> handleGreeting(
            @DestinationVariable String name,
            Mono<String>text
    ) {

        return text
                .doOnNext(greeting -> log.info("\nReceived message: {} \nfrom: {}", greeting, name ))
                .map(greeting -> "Hello get back");

    }

}
