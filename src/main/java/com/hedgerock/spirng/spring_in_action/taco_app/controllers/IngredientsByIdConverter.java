package com.hedgerock.spirng.spring_in_action.taco_app.controllers;

import com.hedgerock.spirng.spring_in_action.taco_app.data.ingredients_repository.IngredientsRepository;
import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientsByIdConverter implements Converter<String, Ingredients> {

    private final IngredientsRepository ingredientsRepository;

    public IngredientsByIdConverter(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public Ingredients convert(String id) {
        return this.ingredientsRepository.findById(id).orElse(null);
    }
}
