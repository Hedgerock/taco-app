package com.hedgerock.spirng.spring_in_action.view_taco_app.data.ingredients_repository;


import com.hedgerock.spirng.spring_in_action.view_taco_app.model.Ingredients;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class JdbcIngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterable<Ingredients> findAllIngredients() {
        String sql = "SELECT * FROM ingredients";

        return this.jdbcTemplate.query(sql, this::mapRowToIngredients);
    }

    public Optional<Ingredients> findIngredientById(String id) {
        String sql = "SELECT * FROM ingredients WHERE id = ?";

        List<Ingredients>ingredients =
                this.jdbcTemplate.query(sql,this::mapRowToIngredients, id);

        return ingredients.isEmpty()
                ? Optional.empty()
                : Optional.of(ingredients.get(0));
    }

    public Ingredients saveIngredient(Ingredients ingredient) {
        String sql = "INSERT INTO ingredients (id, name, type) VALUES (?, ?, ?)";

        String id = ingredient.getId();
        String name = ingredient.getName();
        Ingredients.Type type = ingredient.getType();

        this.jdbcTemplate.update(sql, id, name, type);

        return ingredient;
    }

    private Ingredients mapRowToIngredients(ResultSet row, int i) throws SQLException {
        return new Ingredients(
                row.getString("id"),
                row.getString("name"),
                Ingredients.Type.valueOf(row.getString("type"))
        );
    }
}
