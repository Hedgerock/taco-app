package com.hedgerock.spirng.spring_in_action.reactor.introduction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class LogicalFunc extends Functions {

    public static void main(String[] args) {

        all();
        any();

    }

    private static void all() {
        Flux<String> characters = getReadyCharacters();
        Mono<Boolean> isLarge = characters.all(el -> el.length() > 2);

        subscribe("all", isLarge);
    }

    private static void any() {
        Flux<String> characters = getReadyCharacters();
        Mono<Boolean> isLarge = characters.any(el -> el.length() > 5);

        subscribe("any", isLarge);
    }

}
