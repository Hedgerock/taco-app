package com.hedgerock.spirng.spring_in_action.reactor.reactiveController;

import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.data.IngredientRepository;
import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.model.Ingredient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class ReactiveController {

    @RestController
    @RequestMapping(value = "/api/ingredients", produces = "application/json")
    @CrossOrigin(origins = "http://localhost:8080")
    public class IngredientsController {

        private final IngredientRepository reactiveRepository;
        private final WebClient webClient;

        public IngredientsController(IngredientRepository reactiveRepository, WebClient webClient) {
            this.reactiveRepository = reactiveRepository;
            this.webClient = webClient;
        }

        @GetMapping
        public Flux<Ingredient> allIngredients() {
            return this.reactiveRepository.findAll();
        }

        private void getOptions() {
            //        Mono<Ingredients> ingredientsMono = WebClient.create()
//                .get()
//                .uri("http://localhost:8080/api/ingredients/{id}", ingredientId)
//                .retrieve()
//                .bodyToMono(Ingredients.class);
//
//
//        ingredientsMono.subscribe(i -> {....});

            Flux<Ingredient> ingredientsFlux = this.webClient
                    .get()
                    .uri("/api/ingredients")
                    .retrieve()
                    .bodyToFlux(Ingredient.class);


            Flux<Ingredient> currentIngredient = this.webClient
                    .get()
                    .uri("/api/ingredients/{id}", "currentId")
                    .exchangeToFlux(clientResponse ->
                            clientResponse.headers().header("X_UNAVAILABLE").contains("true")
                                    ? Flux.empty()
                                    : Flux.just(clientResponse)
                    )
                    .flatMap(ingr -> ingr.bodyToFlux(Ingredient.class));

//                .onStatus(HttpStatusCode::is4xxClientError, response ->
//                        Mono.just(new UnknownIngredientException("Ingredient not found")))


//        ingredientsFlux.timeout(5);
        }

        private void postOptions() {
            Mono<Ingredient> ingredientsMono = Mono.just(
                    new Ingredient("INGC", "Ingredient c", Ingredient.Type.VEGGIES)
            );

            Mono<Ingredient> result = this.webClient
                    .post()
                    .uri("/api/ingredients")
                    .body(ingredientsMono, Ingredient.class)
                    .retrieve()
                    .bodyToMono(Ingredient.class);
        }


        private void putOption() {
            Ingredient ingredients = new Ingredient("INGC", "Ingredient c", Ingredient.Type.VEGGIES);

            Mono<Void> mono = this.webClient
                    .put()
                    .uri("/api/ingredients/{id}", ingredients.getId())
                    .bodyValue(ingredients)
                    .retrieve()
                    .bodyToMono(Void.class);
        }

        private void deleteOption() {
            Ingredient ingredients = new Ingredient("INGC", "Ingredient c", Ingredient.Type.VEGGIES);

            Mono<Void> mono = this.webClient
                    .delete()
                    .uri("/api/ingredients/{id}", "ingredientId")
                    .retrieve()
                    .bodyToMono(Void.class);
        }

    }

}
