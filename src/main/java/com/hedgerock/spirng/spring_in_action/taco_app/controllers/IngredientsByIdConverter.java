package com.hedgerock.spirng.spring_in_action.taco_app.controllers;

import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients.Type.*;
import static com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients.Type.SAUCE;

@Component
public class IngredientsByIdConverter implements Converter<String, Ingredients> {
    private final Map<String, Ingredients> ingredientsMap = new HashMap<>();

    public IngredientsByIdConverter() {
        ingredientsMap.putAll(
                Map.of(
                        "FLTO",
                        new Ingredients("FLTO", "Flour Tortilla", WRAP),
                        "COTO",
                        new Ingredients("COTO", "Corn Tortilla", WRAP),
                        "GRBF",
                        new Ingredients("GRBF", "Ground Beef", PROTEIN),
                        "CARN",
                        new Ingredients("CARN", "Carnitas", PROTEIN),
                        "TMTO",
                        new Ingredients("TMTO", "Diced Tomatoes", VEGETABLES),
                        "LETC",
                        new Ingredients("LETC", "Lettuce", VEGETABLES),
                        "CHED",
                        new Ingredients("CHED", "Cheddar", CHEESE),
                        "JACK",
                        new Ingredients("JACK", "Monterrey Jack", CHEESE),
                        "SLSA",
                        new Ingredients("SLSA", "Salsa", SAUCE),
                        "SRCR",
                        new Ingredients("SRCR", "Sour Cream", SAUCE)
                )
        );

    }

    @Override
    public Ingredients convert(String id) {
        return this.ingredientsMap.get(id);
    }
}
