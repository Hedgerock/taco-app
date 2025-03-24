package com.hedgerock.spirng.spring_in_action.taco_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

@Data
@Entity
@Table(name="ingredients")
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@RestResource(rel = "ingredients", path="ingredients")
public class Ingredients {

    @Id
    private final String id;

    private final String name;
    private final Type type;

    public enum Type {
        WRAP, PROTEIN, VEGETABLES, CHEESE, SAUCE
    }
}
