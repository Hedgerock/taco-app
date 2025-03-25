package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.jms_artemis;

import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;

public interface OrderMessaging {

    void sendOrder(TacoOrder tacoOrder);

}
