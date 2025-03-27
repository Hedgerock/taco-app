package com.hedgerock.spirng.spring_in_action.reactor.r2dbc.data;


import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.model.TacoOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TacoOrderRepository extends ReactiveCrudRepository<TacoOrder, Long> {
}
