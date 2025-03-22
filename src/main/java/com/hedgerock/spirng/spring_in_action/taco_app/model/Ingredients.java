package com.hedgerock.spirng.spring_in_action.taco_app.model;

import lombok.Data;

@Data
public class Ingredients {

    private final String id;
    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGETABLES, CHEESE, SAUCE
    }
}
