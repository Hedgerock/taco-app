package com.hedgerock.spirng.spring_in_action.taco_app.data.ingredients_repository;

import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientRepository implements IngredientsRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Iterable<Ingredients> findAllIngredients() {
        String sql = "SELECT * FROM ingredients";

        return this.jdbcTemplate.query(sql, this::mapRowToIngredients);
    }

    @Override
    public Optional<Ingredients> findIngredientById(String id) {
        String sql = "SELECT * FROM ingredients WHERE id = ?";

        List<Ingredients>ingredients =
                this.jdbcTemplate.query(sql,this::mapRowToIngredients, id);

        return ingredients.isEmpty()
                ? Optional.empty()
                : Optional.of(ingredients.get(0));
    }

    @Override
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
