package com.hedgerock.spirng.spring_in_action.reactor.introduction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

public abstract class Functions {

    protected static Flux<String> getReadyCharacters(long duration) {
        if (duration < 0) {
            throw new RuntimeException("can't be less than zero");
        }

        return getFluxWithDuration(duration, "Garfield", "Sonic", "Morty", "Neo", "Scooby");

    }

    protected static Flux<String> getReadyCharacters() {
        return getFluxWithoutDuration("Garfield", "Sonic", "Morty", "Neo", "Scooby");
    }


    protected static Flux<String> getReadyFruits(long duration) {
        if (duration < 0) {
            throw new RuntimeException("can't be less than zero");
        }

        return getFluxWithDuration(duration, "Lasagna", "Pizza", "Apple", "Twinkie", "Scooby snack");

    }

    protected static Flux<String> getReadyFruits() {
        return getFluxWithoutDuration("Lasagna", "Pizza", "Apple", "Twinkie", "Scooby snack");
    }

    protected static <F extends Flux<?>> void subscribe(String title, F content) {
        head(title);
        content.subscribe(System.out::println);
        footer(title);
    }

    protected static <F extends Flux<?>> void subscribe(F content, CountDownLatch latch) {
        content.subscribe(
                System.out::println,
                Throwable::printStackTrace,
                latch::countDown
        );
    }

    protected static <M extends Mono<?>> void subscribe(String title, M content) {
        head(title);
        content.subscribe(System.out::println);
        footer(title);
    }

    protected static <M extends Mono<?>> void subscribe(M content, CountDownLatch latch) {
        content.subscribe(
                System.out::println,
                Throwable::printStackTrace,
                latch::countDown
        );
    }

    protected static void head(String title) {
        System.out.printf("_________________________Start %s___________________________\n", title);
    }

    protected static void footer(String title) {
        System.out.printf("___________________________End %s___________________________\n", title);
        System.out.println("\n");
    }


    @SafeVarargs
    protected static <F> Flux<F> getFluxWithDuration(long duration, F... args) {
        return duration >= 0
                ? Flux
                .just(args)
                .delayElements(Duration.ofMillis(duration))
                : getFluxWithoutDuration(args);

    }

    @SafeVarargs
    protected static <F> Flux<F> getFluxWithoutDuration(F... args) {
        return Flux.just(args);
    }

    protected static String getAnswer(long total) {
        return String.format("Value can't be more than %d", Math.max(total-1, 0));
    }

    protected static String countCharacters(String element) {
        int length = element.length();
        String suffix = length == 1 ? "" : "s";

        return String.format("'%s' has length: %d character%s", element, length, suffix);
    }

    protected static String getAllLettersCapital(String element) {
        return element.toUpperCase();
    }

}
