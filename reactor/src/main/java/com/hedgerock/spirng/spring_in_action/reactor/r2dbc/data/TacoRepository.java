package com.hedgerock.spirng.spring_in_action.reactor.r2dbc.data;


import com.hedgerock.spirng.spring_in_action.reactor.r2dbc.model.Taco;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TacoRepository extends ReactiveCrudRepository<Taco, Long> {}
