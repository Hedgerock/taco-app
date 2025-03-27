package com.hedgerock.spirng.spring_in_action.view_taco_app.model.exceptions;

public class UnknownIngredientException extends RuntimeException {
    public UnknownIngredientException(String message) {
        super(message);
    }
}
