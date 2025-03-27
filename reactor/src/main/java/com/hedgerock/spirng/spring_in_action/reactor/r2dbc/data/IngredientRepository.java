package com.hedgerock.spirng.spring_in_action.reactor.r2dbc.data;


import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.model.Ingredient;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface IngredientRepository extends ReactiveCrudRepository<Ingredient, Long> {

    Mono<Ingredient> findBySlug(String slug);

}
