package com.hedgerock.spirng.spring_in_action.reactor.introduction;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.util.concurrent.CountDownLatch;

public class CombinedFunc extends Functions {

    public static void main(String[] args) throws InterruptedException {
        mergeWith();
        zip();
        firstWithSignal();
    }

    private static void mergeWith() throws InterruptedException {
        String title = "mergeWith";
        long duration = 100;

        head(title);
        CountDownLatch latch = new CountDownLatch(1);

        Flux<String> characters = getReadyCharacters(duration);
        Flux<String> food = getReadyFruits(duration);

        Flux<String> mergedFlux = characters.mergeWith(food);
        subscribe(mergedFlux, latch);
        latch.await();

        footer(title);
    }

    private static void zip() {
        Flux<String> characters = getReadyCharacters();
        Flux<String> food = getReadyFruits();

        Flux<Tuple2<String, String>>zipped = Flux.zip(food, characters);

        subscribe("zip", zipped);
    }

    private static void firstWithSignal() {
        Flux<String> characters = getReadyCharacters();
        Flux<String> food = getReadyFruits();

        Flux<String> firstFlux = Flux.firstWithSignal(characters, food);

        subscribe("firstWithSignal", firstFlux);
    }

}
