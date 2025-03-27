package com.hedgerock.spirng.spring_in_action.view_taco_app.data.ingredients_repository;


import com.hedgerock.spirng.spring_in_action.view_taco_app.model.Ingredients;
import org.springframework.data.repository.CrudRepository;

public interface IngredientsRepository extends CrudRepository<Ingredients, String> {}
