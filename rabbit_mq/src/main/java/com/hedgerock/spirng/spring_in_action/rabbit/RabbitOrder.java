package com.hedgerock.spirng.spring_in_action.rabbit;

import com.hedgerock.spirng.spring_in_action.view_taco_app.model.TacoOrder;

public interface RabbitOrder {

    void sendOrder(TacoOrder tacoOrder);

}
