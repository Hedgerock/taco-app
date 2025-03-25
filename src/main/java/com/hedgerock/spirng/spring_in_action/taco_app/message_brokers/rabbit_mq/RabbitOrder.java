package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.rabbit_mq;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;

public interface RabbitOrder {

    void sendOrder(TacoOrder tacoOrder);

}
