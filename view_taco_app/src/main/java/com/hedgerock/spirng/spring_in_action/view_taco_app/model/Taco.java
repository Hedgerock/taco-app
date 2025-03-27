package com.hedgerock.spirng.spring_in_action.view_taco_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
@RestResource(path="tacos", rel = "tacos")
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="created_at")
    private LocalDate createdAt;

    @NotEmpty(message = "Name can't be empty")
    @NotBlank
    private String name;

    @Size(min=1, message="Select at least 1 ingredient")
    @NotNull
    @ManyToMany(targetEntity = Ingredients.class)
    private List<Ingredients> ingredients;

    @PrePersist
    void createdAt() {
        this.createdAt = LocalDate.now();
    }
}
