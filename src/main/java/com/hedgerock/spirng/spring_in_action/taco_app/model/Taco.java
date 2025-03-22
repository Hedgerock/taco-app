package com.hedgerock.spirng.spring_in_action.taco_app.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class Taco {

    private Long id;

    private LocalDate cratedAt;

    @NotEmpty(message = "Name can't be empty")
    @NotBlank
    private String name;

    @Size(min=1, message="Select at least 1 ingredient")
    @NotNull
    private List<Ingredients> ingredients;

    private List<IngredientRef> ingredientRefs;
}
