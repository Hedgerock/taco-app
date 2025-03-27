package com.hedgerock.spirng.spring_in_action.view_taco_app.controllers.api;

import com.hedgerock.spirng.spring_in_action.view_taco_app.data.ingredients_repository.IngredientsRepository;
import com.hedgerock.spirng.spring_in_action.view_taco_app.model.Ingredients;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class IngredientsController {

    private final IngredientsRepository ingredientsRepository;

    public IngredientsController(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @GetMapping
    public Iterable<Ingredients> allIngredients() {
        return this.ingredientsRepository.findAll();
    }


}
