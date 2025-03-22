package com.hedgerock.spirng.spring_in_action.taco_app.data.ingredients_repository;

import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;

import java.util.Optional;

public interface IngredientsRepository {
    Iterable<Ingredients>findAllIngredients();
    Optional<Ingredients>findIngredientById(String id);
    Ingredients saveIngredient(Ingredients ingredient);
}
