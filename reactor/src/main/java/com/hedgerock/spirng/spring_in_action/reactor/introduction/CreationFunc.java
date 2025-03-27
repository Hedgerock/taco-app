package com.hedgerock.spirng.spring_in_action.reactor.introduction;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

public class CreationFunc extends Functions {

    public static void main(String[] args) throws InterruptedException {
        just();
        fromArray();
        fromIterable();
        fromStream();
        range();
        interval();
    }

    private static void just() {
        Flux<String> fruits = Flux.just("apple", "orange", "banana", "strawberry");
        subscribe("just",fruits);
    }

    private static void fromArray() {
        String[] arr = { "apple", "orange", "banana", "strawberry" };
        Flux<String> fruits = Flux.fromArray(arr);

        subscribe("fromArray",fruits);
    }

    private static void fromIterable() {
        List<String> list = List.of("apple", "orange", "banana", "strawberry");
        Flux<String> fruits = Flux.fromIterable(list);

        subscribe("fromIterable",fruits);
    }

    private static void fromStream() {
        Stream<String> stream = Stream.of("apple", "orange", "banana", "strawberry");
        Flux<String> fruits = Flux.fromStream(stream);

        subscribe("fromStream", fruits);
    }

    private static void range() {
        Flux<Integer>range = Flux.range(1, 5);
        subscribe("range", range);
    }

    private static void interval() throws InterruptedException {
        head("interval");
        CountDownLatch latch = new CountDownLatch(1);
        Flux<Long> longFlux = Flux.interval(Duration.ofSeconds(1)).take(5);
        subscribe(longFlux, latch);
        latch.await();
        footer("interval");
    }

}
