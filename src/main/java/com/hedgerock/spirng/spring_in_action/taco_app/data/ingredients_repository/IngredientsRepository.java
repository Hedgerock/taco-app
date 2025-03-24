package com.hedgerock.spirng.spring_in_action.taco_app.data.ingredients_repository;

import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IngredientsRepository extends CrudRepository<Ingredients, String> {}
