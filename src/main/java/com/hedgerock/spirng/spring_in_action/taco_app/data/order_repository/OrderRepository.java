package com.hedgerock.spirng.spring_in_action.taco_app.data.order_repository;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;

public interface OrderRepository {

    TacoOrder saveTacoOrder(TacoOrder tacoOrder);


}
