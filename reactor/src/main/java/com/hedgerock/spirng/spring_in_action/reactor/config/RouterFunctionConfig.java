package com.hedgerock.spirng.spring_in_action.reactor.config;

import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.data.TacoRepository;
import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.model.Taco;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouterFunctionConfig {

    private final TacoRepository reactiveRepository;

    public RouterFunctionConfig(TacoRepository reactiveRepository) {
        this.reactiveRepository = reactiveRepository;
    }

    @Bean
    public RouterFunction<?> helloRouter() {

        return RouterFunctions.
                route (
                        GET("/api/tacos").and(queryParam("recent", Objects::nonNull)),
                        this::recents
                )
                .andRoute (
                        POST("/api/tacos"),
                        this::postTaco
                );
    }

    public Mono<ServerResponse> recents(ServerRequest serverRequest) {
        return ok().body(this.reactiveRepository.findAll().take(12), Taco.class);
    }

    public Mono<ServerResponse> postTaco(ServerRequest serverRequest) {
        return serverRequest
                .bodyToMono(Taco.class)
                .flatMap(this.reactiveRepository::save)
                .flatMap(savedTaco ->
                        ServerResponse
                            .created(URI.create("http://localhost:8080/api/tacos/" + savedTaco.getId()))
                            .body(savedTaco, Taco.class)
                );
    }


    @Bean
    public WebClient webClient() {
        return WebClient.create("http://localhost:8080");
    }
}
