package com.hedgerock.spirng.spring_in_action.view_taco_app.data.order_repository;

import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {}
