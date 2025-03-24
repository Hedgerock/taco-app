package com.hedgerock.spirng.spring_in_action.taco_app.data.order_repository;

import com.hedgerock.spirng.spring_in_action.taco_app.model.IngredientRef;
import com.hedgerock.spirng.spring_in_action.taco_app.model.Ingredients;
import com.hedgerock.spirng.spring_in_action.taco_app.model.Taco;
import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder saveTacoOrder(TacoOrder tacoOrder) {
        String sql = "INSERT INTO taco_order " +
                "(" +
                "delivery_name, delivery_street, delivery_city, " +
                "delivery_state, delivery_zip, credit_card_number, " +
                "credit_card_expiration_date, credit_card_cvv, placed_at" +
                ") " +
                "VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        var prepareStatementFactory = new PreparedStatementCreatorFactory(
            sql,
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );

        prepareStatementFactory.setReturnGeneratedKeys(true);

        tacoOrder.setCreatedAt(LocalDate.now());

        var prepareStatementCreator = prepareStatementFactory.newPreparedStatementCreator(
                Arrays.asList(
                        tacoOrder.getDeliveryName(),
                        tacoOrder.getDeliveryStreet(),
                        tacoOrder.getDeliveryCity(),
                        tacoOrder.getDeliveryState(),
                        tacoOrder.getDeliveryZip(),
                        tacoOrder.getCreditCardNumber(),
                        tacoOrder.getCreditCardExpirationDate(),
                        tacoOrder.getCreditCardCvv(),
                        tacoOrder.getCreatedAt()
                )
        );

        var keyHolder = new GeneratedKeyHolder();

        this.jdbcOperations.update(prepareStatementCreator, keyHolder);

        Long orderId = keyHolder.getKey().longValue();
        tacoOrder.setId(orderId);

        List<Taco> tacos = tacoOrder.getTacos();

        int i = 0;

        for (Taco taco: tacos) {
            saveTaco(orderId, i++, taco);
        }

        return tacoOrder;
    }

    private Long saveTaco(Long orderId, int orderKey, Taco taco) {
        String sql = "INSERT INTO tacos " +
                "(" +
                "name, created_at, taco_order, taco_order_key" +
                ") " +
                "VALUES " +
                "(?, ?, ?, ?)";

        var prepareStatementFactory = new PreparedStatementCreatorFactory(
                sql, Types.VARCHAR, Types.TIMESTAMP, Types.BIGINT, Types.BIGINT
        );

        prepareStatementFactory.setReturnGeneratedKeys(true);
        taco.setCratedAt(LocalDate.now());

        var prepareStatementCreator = prepareStatementFactory.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        taco.getCratedAt(),
                        orderId,
                        orderKey
                )
        );

        var keyHolder = new GeneratedKeyHolder();
        this.jdbcOperations.update(prepareStatementCreator, keyHolder);

        Long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveIngredientsRef(tacoId, taco.getIngredients());

        return tacoId;
    }

    private void saveIngredientsRef(Long id, List<Ingredients> ingredients) {

        int key = 0;
        String sql = "INSERT INTO ingredient_ref " +
                "(ingredient, taco, taco_key) " +
                "VALUES " +
                "(?, ?, ?)";

        for(Ingredients ingredientRef: ingredients) {
            this.jdbcOperations.update(sql, ingredientRef.getId(), id, key++);
        }
    }

}
