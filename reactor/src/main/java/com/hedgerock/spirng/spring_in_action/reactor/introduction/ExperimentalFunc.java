package com.hedgerock.spirng.spring_in_action.reactor.introduction;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class ExperimentalFunc extends FilterFunc {

    public static void main(String[] args) {

        bufferWithFlatMapAndFromIterable(2);
        monoOfFluxes();
        mapCombination();
    }

    private static void bufferWithFlatMapAndFromIterable(int elementsInBuffer) {
        Flux<String> fruits = getReadyFruits();
        Flux<List<String>> bufferedFlux = fruits
                .buffer(elementsInBuffer)
                .flatMap(list -> Flux.fromIterable(list).map(FilterFunc::getAllLettersCapital).collectList());

        subscribe("bufferWithFlatMap", bufferedFlux);
    }

    private static void monoOfFluxes() {
        Flux<String> characters = getReadyCharacters();
        Mono<List<String>> mono = characters.collectList();

        subscribe("monoOfFluxes", mono);

    }

    private static void mapCombination() {
        Flux<String> fruits = getReadyFruits();
        Mono<Map<Character, String>> mapMono = fruits.collectMap(component -> component.charAt(0));

        subscribe("mapCombination", mapMono);
    }

}
