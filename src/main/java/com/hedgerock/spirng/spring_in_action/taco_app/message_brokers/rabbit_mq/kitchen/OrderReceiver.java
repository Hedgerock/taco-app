package com.hedgerock.spirng.spring_in_action.taco_app.message_brokers.rabbit_mq.kitchen;


import com.hedgerock.spirng.spring_in_action.taco_app.model.TacoOrder;

public interface OrderReceiver {

    TacoOrder receiveOrder();

}
