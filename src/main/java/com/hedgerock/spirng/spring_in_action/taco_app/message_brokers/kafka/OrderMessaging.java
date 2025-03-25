package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.kafka;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;

public interface OrderMessaging {

    void sendOrder(TacoOrder tacoOrder);

}
