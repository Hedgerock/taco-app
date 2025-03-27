package com.hedgerock.spirng.spring_in_action.reactor.introduction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class FilterFunc extends Functions {

    public static void main(String[] args) throws InterruptedException {
        skip(2);
//        skipWithDuration(4000);
        take(3);

        filter();
        distinct();
        map();
        flatMap();
        buffer(2);
    }

    private static void skip(int totalToSkip) {
        Flux<String> characters = getReadyCharacters();

        long total = characters.count().block();
        boolean isEmpty = totalToSkip >= total;

        if (isEmpty) {
            System.out.println(getAnswer(total));
            return;
        }

        Flux<String> slicedCharacters = characters
                .skip(totalToSkip);

        subscribe("skip", slicedCharacters);
    }

    private static void skipWithDuration(long duration) throws InterruptedException {
        if (duration < 1000) {
            System.out.println("Please provide at least 1000 millis");
            return;
        }

        CountDownLatch latch = new CountDownLatch(1);
        String title = "skipWithDuration";

        head(title);

        Flux<String> characters = getReadyCharacters()
                .delayElements(Duration.ofMillis(1000))
                .skip(Duration.ofMillis(duration));

        subscribe(characters, latch);
        latch.await();

        footer(title);
    }

    private static void take(long elementsToTake) {
        Flux<String> characters = getReadyCharacters()
                .take(elementsToTake);

        subscribe("take", characters);
    }

    private static void filter() {
        Flux<String> characters = getReadyCharacters()
                .filter(el -> el.length() > 5);

        subscribe("filter", characters);
    }

    private static void distinct() {
        Flux<String> fruits = getReadyFruits()
                .mergeWith(getReadyFruits())
                .distinct();

        subscribe("distinct", fruits);
    }

    private static void map() {

        Flux<String> fruits = getReadyFruits()
                .map(FilterFunc::countCharacters);

        subscribe("map", fruits);

    }

    private static void flatMap() {
        Flux<String> characters = getReadyCharacters().flatMap(character -> {
            return Mono.just(character).map(FilterFunc::countCharacters);
        });

        subscribe("flatMap", characters);

    }

    private static void buffer(int elementsInBuffer) {
        Flux<String>fruits = getReadyFruits();
        Flux<List<String>> bufferedFlux = fruits.buffer(elementsInBuffer);

        subscribe("buffer", bufferedFlux);
    }

}
